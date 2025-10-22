
import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api/auth",
});

export const register = (email: string, password: string) =>
  API.post("/register", { email, password });

export const login = (email: string, password: string) =>
  API.post("/login", { email, password });
