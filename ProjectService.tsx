
import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api/project",
});

API.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");
  if (token) req.headers.Authorization = `Bearer ${token}`;
  return req;
});

export const addProject = (data: { title: string; description: string; }) => API.post("/add", data);
export const getProjects = () => API.get("/all");
