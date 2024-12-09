import React, { useState, useEffect } from "react";
import Formulario from "./components/Formulario";
import Lista from "./components/Lista";
import "./assets/styles/App.scss";
import logo from "./assets/img/powerpdf-logo.png";
import logo2 from "./assets/img/logo-powercrm.png";

const App = () => {
  const [pdfs, setPdfs] = useState([]);

  const atualizarListaPdfs = () => {
    fetchPdfs(); 
  };

  const fetchPdfs = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/pdfs/listar");
      const data = await response.json();
      setPdfs(data); 
    } catch (error) {
      console.error("Erro ao buscar PDFs:", error);
    }
  };

  useEffect(() => {
    fetchPdfs(); 
  }, []);

  return (
    <div className="container">
      <section className="head">
        <img src={logo} alt="Logo" title="Logo da pagina PowerPDF"/>
      </section>
      <section className="forms">
      <Formulario onCadastroSuccess={atualizarListaPdfs} />
      </section>
      <section className="list">
      <Lista pdfs={pdfs} />
      </section>
      <section className="footer">
        <img src={logo2} alt="Logo" title="Logo da pagina PowerPDF"/>
      </section>
    </div>
  );
};

export default App;
