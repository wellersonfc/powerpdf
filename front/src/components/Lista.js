import React from "react";
import icon1 from "../assets/img/icon1.png";
import icon2 from "../assets/img/icon2.png";
import icon3 from "../assets/img/icon3.png";

const Lista = ({ pdfs }) => {
  return (
    <div className="lista">
      <div className="mostrar-total">
        <strong>Merges</strong> <span>{pdfs.length}</span>
      </div>

      {/* Filtros */}
      <div className="filtros">
        <button onClick={() => window.location.reload()}>Limpar Filtro</button>
      </div>

      {pdfs.length === 0 ? (
        <div className="lista-vazia">
          <img src={icon3} alt="Logo" title="Logo da pagina PowerPDF" />
          <strong><p>Nenhum merge encontrado</p></strong>
          <p>Junte seus PDF`s em um único documento</p>
        </div>
      ) : (
        <ul className="lista-pdfs">
          {pdfs.map((pdf, index) => (
            <li key={index} className="item-pdf">
              <div><img src={icon1} alt="Logo" title="Logo da pagina PowerPDF" />{pdf.dataCadastroPdf}</div>
              <div><span><img src={icon2} alt="Logo" title="Logo da pagina PowerPDF" />{pdf.nomePdf}</span></div>
              <div>
                <a href={`http://localhost:8080/api/pdfs/${pdf.nomePdf}`} target="_blank" rel="noopener noreferrer">
                  <button className="button-principal">Download</button>
                </a>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Lista;
