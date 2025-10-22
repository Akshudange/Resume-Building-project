import { useEffect, useState } from "react";
import { addProject, getProjects } from "../services/projectService";

type Project = {
  id: number;
  title: string;
  description: string;
};

export default function Projects() {
  const [projects, setProjects] = useState<Project[]>([]);
  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");

  useEffect(() => {
    getProjects().then((res) => setProjects(res.data));
  }, []);

  const handleAdd = async (e: React.FormEvent) => {
    e.preventDefault();
    await addProject({ title, description: desc });
    alert("Project added");
    // refresh the list
    getProjects().then((res) => setProjects(res.data));
  };

  return (
    <div>
      <h2>Projects</h2>
      <form onSubmit={handleAdd}>
        <input
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <input
          placeholder="Description"
          value={desc}
          onChange={(e) => setDesc(e.target.value)}
        />
        <button type="submit">Add</button>
      </form>
      <ul>
        {projects.map((p) => (
          <li key={p.id}>{p.title}</li>
        ))}
      </ul>
    </div>
  );
}
