import { useEffect, useState } from "react";
import API from "../services/api";

export default function Dashboard() {

  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.id;

  if (!user) {
    return <h2>Please login first</h2>;
  }

  const [jobs, setJobs] = useState([]);

  const [newJob, setNewJob] = useState({
    companyName: "",
    role: "",
    status: "Applied"
  });

  const fetchJobs = async () => {
    if (!userId) return;
    const res = await API.get(`/jobs/${userId}`);
    setJobs(res.data);
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  const handleAddJob = async () => {
    await API.post(`/jobs/${userId}`, newJob);
    fetchJobs();
  };

  const updateStatus = async (jobId, status) => {
    await API.put(`/jobs/${jobId}?status=${status}`);
    fetchJobs();
  };

  const deleteJob = async (jobId) => {
    await API.delete(`/jobs/${jobId}`);
    fetchJobs();
  };

  return (
    <div style={{
      maxWidth: "800px",
      margin: "auto",
      padding: "20px",
      fontFamily: "Arial"
    }}>

      {/* ✅ Logout button HERE */}
      <button
        onClick={() => {
          localStorage.removeItem("user");
          window.location.href = "/";
        }}
        style={{ float: "right" }}
      >
        Logout
      </button>

      <h2>Job Dashboard</h2>

      {/* Add Job */}
      <div style={{
        background: "#f9f9f9",
        padding: "15px",
        borderRadius: "10px",
        marginBottom: "20px"
      }}>

        <div>
          <input
            placeholder="Company"
            onChange={(e) =>
              setNewJob({ ...newJob, companyName: e.target.value })
            }
          />
          <br /><br />

          <input
            placeholder="Role"
            onChange={(e) =>
              setNewJob({ ...newJob, role: e.target.value })
            }
          />
          <br /><br />

          <button style={{
            padding: "8px 15px",
            background: "#4f46e5",
            color: "white",
            border: "none",
            borderRadius: "5px"
          }} onClick={handleAddJob}>  Add Job</button>
        </div>
      </div>

      {/* Jobs List */}
      {jobs.map((job) => (
        <div key={job.id}>
          <h3>{job.companyName}</h3>
          <p>{job.role}</p>
          <p>{job.status}</p>

          <button onClick={() => updateStatus(job.id, "Interview")}>
            Interview
          </button>

          <button onClick={() => deleteJob(job.id)}>
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}