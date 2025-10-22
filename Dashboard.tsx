
// import Sidebar from "../components/Sidebar";
import Sidebar from "../component/Sidebar";

export default function Dashboard() {
  return (
    <div className="dashboard">
      <Sidebar />
      <div className="content">
        <h2>Welcome to Resume Ecosystem Dashboard</h2>
        <p>Manage your resume, projects, and skills in one place!</p>
      </div>
    </div>
  );
}
