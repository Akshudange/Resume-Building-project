import React, { createContext, useState, useContext, ReactNode } from "react";
import { useNavigate } from "react-router-dom";

// Define the type of your context
interface AuthContextType {
  token: string;
  login: (token: string) => void;
  logout: () => void;
}

// Create the context with a default value of null
export const AuthContext = createContext<AuthContextType | null>(null);

// Create a custom hook (for easier use in components)
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

// AuthProvider component
export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem("token") || "");
  const navigate = useNavigate();

  const login = (token: string) => {
    localStorage.setItem("token", token);
    setToken(token);
    navigate("/dashboard");
  };

  const logout = () => {
    localStorage.removeItem("token");
    setToken("");
    navigate("/");
  };

  return (
    <AuthContext.Provider value={{ token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
