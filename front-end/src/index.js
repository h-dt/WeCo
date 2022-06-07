import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";

const root = ReactDOM.createRoot(document.getElementById("root"));
document.cookie = "safeCookie1foo; SameSite=Lax";
document.cookie = "safeCookie1foo";
document.cookie = "crossCookie=bar; SameSite=None; Secure";
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
