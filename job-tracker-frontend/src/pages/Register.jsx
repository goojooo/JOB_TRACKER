import { useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const [user, setUser] = useState({
    name: "",
    email: "",
    password: ""
  });

  const navigate = useNavigate();

  const handleRegister = async () => {
    try {
      await API.post("/auth/register", user);
      alert("Registered successfully ✅");

      navigate("/");
    } catch (err) {
      alert("Error ❌");
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h2>Register</h2>

      <input
        placeholder="Name"
        value={user.name}
        onChange={(e) =>
          setUser({ ...user, name: e.target.value })
        }
      />
      <br /><br />

      <input
        placeholder="Email"
        value={user.email}
        onChange={(e) =>
          setUser({ ...user, email: e.target.value })
        }
      />
      <br /><br />

      <input
        type="password"
        placeholder="Password"
        value={user.password}
        onChange={(e) =>
          setUser({ ...user, password: e.target.value })
        }
      />
      <br /><br />

      <button onClick={handleRegister}>Register</button>

      {/* 👇 Clean link */}
      <p style={{ marginTop: "10px", fontSize: "14px" }}>
        Already have an account?{" "}
        <span
          style={{ color: "#7c3aed", cursor: "pointer" }}
          onClick={() => navigate("/")}
        >
          Login
        </span>
      </p>
    </div>
  );
}