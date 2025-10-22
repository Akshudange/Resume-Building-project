
import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api/skill",
});

API.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");
  if (token) req.headers.Authorization = `Bearer ${token}`;
  return req;
});

export const addSkill = (data: { name: string; level: string; }) => API.post("/add", data);
export const getSkills = () => API.get("/all");
