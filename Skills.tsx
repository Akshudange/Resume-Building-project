import { useEffect, useState } from "react";
import { addSkill, getSkills } from "../services/skillService";

type Skill = {
  id: number;
  name: string;
  level?: string;
};

export default function Skills() {
  const [skills, setSkills] = useState<Skill[]>([]);
  const [name, setName] = useState("");
  const [level, setLevel] = useState("");

  useEffect(() => {
    getSkills()
      .then((res) => setSkills(res.data))
      .catch(() => alert("Error fetching skills"));
  }, []);

  const handleAdd = async (e: React.FormEvent) => {
    e.preventDefault();
    await addSkill({ name, level });
    alert("Skill added");

    // refresh the list
    getSkills().then((res) => setSkills(res.data));
  };

  return (
    <div>
      <h2>Skills</h2>
      <form onSubmit={handleAdd}>
        <input
          placeholder="Skill Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          placeholder="Level"
          value={level}
          onChange={(e) => setLevel(e.target.value)}
        />
        <button type="submit">Add</button>
      </form>

      <ul>
        {skills.map((s) => (
          <li key={s.id}>{s.name}</li>
        ))}
      </ul>
    </div>
  );
}
