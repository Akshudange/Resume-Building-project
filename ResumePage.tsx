import React, { useState, ChangeEvent } from 'react';
import axios from 'axios';
import './ResumePage.css';

interface Education { degree: string; institution: string; year: string; }
interface Experience { role: string; company: string; duration: string; }
interface Project { title: string; description: string; }

interface Resume {
  name: string;
  email: string;
  phone: string;
  headline: string;
  summary: string;
  education: Education[];
  skills: string[];
  experiences: Experience[];
  projects: Project[];
}

type ArrayNameObj = 'education' | 'experiences' | 'projects';
type ArrayNamePrim = 'skills';
type ArrayNameGoal = 'education' | 'skills' | 'experiences' | 'projects';

const ResumePage = () => {
  const [resume, setResume] = useState<Resume>({
    name: '',
    email: '',
    phone: '',
    headline: '',
    summary: '',
    education: [{ degree: '', institution: '', year: '' }],
    skills: [''],
    experiences: [{ role: '', company: '', duration: '' }],
    projects: [{ title: '', description: '' }],
  });

  const [errors, setErrors] = useState<{ [key: string]: string }>({});

  // 📘 Input handlers
  const handleInputChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, field: keyof Resume) => {
    setResume({ ...resume, [field]: e.target.value });
  };

  const handleNestedChange = (e: ChangeEvent<HTMLInputElement>, index: number, arrayName: ArrayNameObj, field: string) => {
    const list = [...(resume[arrayName] as any[])];
    const current = list[index];
    if (current == null || typeof current === 'object') {
      list[index] = { ...(typeof current === 'object' ? current : {}), [field]: e.target.value };
    } else {
      list[index] = { ...current, [field]: e.target.value };
    }
    setResume({ ...resume, [arrayName]: list });
  };

  const handleArrayChange = (e: ChangeEvent<HTMLInputElement>, index: number, arrayName: ArrayNamePrim) => {
    const list = [...resume[arrayName] as string[]];
    list[index] = e.target.value;
    setResume({ ...resume, [arrayName]: list });
  };

  const addItem = (arrayName: ArrayNameGoal) => {
    if (arrayName === 'education') setResume({ ...resume, education: [...resume.education, { degree: '', institution: '', year: '' }] });
    else if (arrayName === 'experiences') setResume({ ...resume, experiences: [...resume.experiences, { role: '', company: '', duration: '' }] });
    else if (arrayName === 'projects') setResume({ ...resume, projects: [...resume.projects, { title: '', description: '' }] });
    else setResume({ ...resume, [arrayName]: [...resume[arrayName], ''] });
  };

  // 📋 Validation function
  const validateForm = (): boolean => {
    const newErrors: { [key: string]: string } = {};

    if (!resume.name.trim()) newErrors.name = 'Name is required.';
    if (!resume.email.trim()) newErrors.email = 'Email is required.';
    else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(resume.email)) newErrors.email = 'Enter a valid email address.';

    if (!resume.phone.trim()) newErrors.phone = 'Phone number is required.';
    else if (!/^\d{10}$/.test(resume.phone)) newErrors.phone = 'Enter a valid 10-digit phone number.';

    if (!resume.headline.trim()) newErrors.headline = 'Headline is required.';
    if (!resume.summary.trim()) newErrors.summary = 'Summary is required.';

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // 💾 Save resume
  const handleSave = () => {
    if (!validateForm()) {
      alert('Please correct the highlighted fields.');
      return;
    }

    const token = localStorage.getItem('token');
    axios.post('http://localhost:8081/api/resume/update', resume, {
      headers: { Authorization: `Bearer ${token}` },
    })
      .then(() => alert('Resume saved successfully!'))
      .catch(err => {
        console.error('Error saving resume:', err);
        alert('Failed to save resume.');
      });
  };

  return (
    <div className="resume-container">
      <h2>Resume Editor</h2>

      <section className="personal-info">
        <h3>Personal Information</h3>

        <div className="input-group">
          <label>Name:</label>
          <input type="text" value={resume.name} onChange={e => handleInputChange(e, 'name')} />
          {errors.name && <span className="error">{errors.name}</span>}
        </div>

        <div className="input-group">
          <label>Email:</label>
          <input type="email" value={resume.email} onChange={e => handleInputChange(e, 'email')} />
          {errors.email && <span className="error">{errors.email}</span>}
        </div>

        <div className="input-group">
          <label>Phone:</label>
          <input type="text" value={resume.phone} onChange={e => handleInputChange(e, 'phone')} />
          {errors.phone && <span className="error">{errors.phone}</span>}
        </div>

        <div className="input-group">
          <label>Headline:</label>
          <input type="text" value={resume.headline} onChange={e => handleInputChange(e, 'headline')} />
          {errors.headline && <span className="error">{errors.headline}</span>}
        </div>

        <div className="input-group">
          <label>Summary:</label>
          <textarea value={resume.summary} onChange={e => handleInputChange(e, 'summary')} />
          {errors.summary && <span className="error">{errors.summary}</span>}
        </div>
      </section>

      {/* Education Section */}
      <section className="section">
        <h3>Education</h3>
        {resume.education.map((edu, idx) => (
          <div key={idx} className="nested-inputs">
            <input placeholder="Degree" value={edu.degree} onChange={e => handleNestedChange(e, idx, 'education', 'degree')} />
            <input placeholder="Institution" value={edu.institution} onChange={e => handleNestedChange(e, idx, 'education', 'institution')} />
            <input placeholder="Year" value={edu.year} onChange={e => handleNestedChange(e, idx, 'education', 'year')} />
          </div>
        ))}
        <button type="button" onClick={() => addItem('education')}>Add Education</button>
      </section>

      {/* Skills */}
      <section className="section">
        <h3>Skills</h3>
        <div className="array-inputs">
          {resume.skills.map((skill, idx) => (
            <input key={idx} placeholder="Enter Skill Name" value={skill} onChange={e => handleArrayChange(e, idx, 'skills')} />
          ))}
        </div>
        <button type="button" onClick={() => addItem('skills')}>Add Skill</button>
      </section>

      {/* Experience */}
      <section className="section">
        <h3>Experience</h3>
        {resume.experiences.map((exp, idx) => (
          <div key={idx} className="nested-inputs">
            <input placeholder="Role" value={exp.role} onChange={e => handleNestedChange(e, idx, 'experiences', 'role')} />
            <input placeholder="Company" value={exp.company} onChange={e => handleNestedChange(e, idx, 'experiences', 'company')} />
            <input placeholder="Duration" value={exp.duration} onChange={e => handleNestedChange(e, idx, 'experiences', 'duration')} />
          </div>
        ))}
        <button type="button" onClick={() => addItem('experiences')}>Add Experience</button>
      </section>

      {/* Projects */}
      <section className="section">
        <h3>Projects</h3>
        {resume.projects.map((proj, idx) => (
          <div key={idx} className="project-inputs">
            <input type="text" placeholder="Project Title" value={proj.title} onChange={e => handleNestedChange(e, idx, 'projects', 'title')} />
            <input type="text" placeholder="Project Description" value={proj.description} onChange={e => handleNestedChange(e, idx, 'projects', 'description')} />
          </div>
        ))}
        <button type="button" onClick={() => addItem('projects')}>Add Project</button>
      </section>

      <div style={{ marginTop: '20px' }}>
        <button className="save-btn" type="button" onClick={handleSave}>Save</button>
      </div>
    </div>
  );
};

export default ResumePage;
