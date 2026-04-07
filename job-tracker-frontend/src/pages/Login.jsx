import { useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

const handleLogin = async () => {
  try {
    const res = await API.post("/auth/login", {
      email: email,
      password: password
    });

    localStorage.setItem("user", JSON.stringify(res.data));
    navigate("/dashboard");

  } catch (err) {
    console.error(err);
    alert("Login failed ❌");
  }
};

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h2>Login</h2>

      <input
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <br /><br />

      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <br /><br />

      <button onClick={handleLogin}>Login</button>

      {/* 👇 Clean link */}
      <p style={{ marginTop: "10px", fontSize: "14px" }}>
        Don't have an account?{" "}
        <span
          style={{ color: "#7c3aed", cursor: "pointer" }}
          onClick={() => navigate("/register")}
        >
          Register
        </span>
      </p>
    </div>
  );
}