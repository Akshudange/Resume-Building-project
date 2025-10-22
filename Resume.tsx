import { useEffect, useState } from "react";
import { getResume } from "../services/resumeService";

export default function Resume() {
  const [resume, setResume] = useState<any>(null);

  useEffect(() => {
    getResume()
      .then((res) => setResume(res.data))
      .catch(() => alert("Error fetching resume"));
  }, []);

  if (!resume) return <p>Loading...</p>;

  return (
    <div>
      <h2>{resume.user.firstName} {resume.user.lastName}</h2>
      <p>Email: {resume.user.email}</p>
      <h3>Skills</h3>
      <ul>{resume.skills.map((s: any) => <li key={s.id}>{s.name}</li>)}</ul>
      <h3>Projects</h3>
      <ul>{resume.projects.map((p: any) => <li key={p.id}>{p.title}</li>)}</ul>
    </div>
  );
}
