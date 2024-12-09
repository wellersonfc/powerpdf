import React, { useState } from "react";
import { useFormik } from "formik";
import * as Yup from "yup";

const Formulario = ({ onCadastroSuccess }) => {
  const [fileName, setFileName] = useState("Selecione o PDF");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(null);

  const formik = useFormik({
    initialValues: {
      caminhoPdf: "",
      nomePdf: "",
    },
    validationSchema: Yup.object({
      caminhoPdf: Yup.mixed()
        .required("O arquivo PDF é obrigatório")
        .test(
          "fileFormat",
          "O arquivo deve ser um PDF",
          (value) => value && value.type === "application/pdf"
        ),
      nomePdf: Yup.string()
        .required("O nome do PDF é obrigatório")
        .max(50, "O nome deve ter no máximo 50 caracteres"),
    }),
    onSubmit: async (values) => {
      setLoading(true);
      setMessage(null);

      const formData = new FormData();
      formData.append("arquivoPdf", values.caminhoPdf);
      formData.append("nomePdf", values.nomePdf);

      try {
        const response = await fetch("http://localhost:8080/api/pdfs/cadastrar", {
          method: "POST",
          body: formData,
          withCredentials: true,
        });

        if (!response.ok) {
          throw new Error(`Erro: ${response.status} - ${response.statusText}`);
        }

        const data = await response.text(); 
        console.log("Resposta da API:", data);
        setMessage({ type: "success", text: data });
        formik.resetForm();
        setFileName("Selecione o PDF");

        onCadastroSuccess();
      } catch (error) {
        console.error("Erro no cadastro:", error.message || error);
        setMessage({ type: "error", text: "Erro ao cadastrar o PDF. Tente novamente." });
      } finally {
        setLoading(false); 
      }
    },
  });

  const handleFileChange = (event) => {
    const file = event.currentTarget.files[0];
    if (file) {
      setFileName(`Arquivo selecionado: ${file.name}`);
      formik.setFieldValue("caminhoPdf", file);
    } else {
      setFileName("Selecione o PDF");
    }
  };

  return (
    <form onSubmit={formik.handleSubmit}>
      <div className="form-group-file">
        <label className="label" htmlFor="caminhoPdf">
          <div className="input-label">{fileName}</div>
          <input
            id="caminhoPdf"
            type="file"
            name="caminhoPdf"
            accept="application/pdf"
            onChange={handleFileChange}
          />
        </label>
      </div>
      <div className="validacao">
        {formik.touched.caminhoPdf && formik.errors.caminhoPdf && (
          <div>{formik.errors.caminhoPdf}</div>
        )}
      </div>

      <div className="form-group-text">
        <input
          id="nomePdf"
          type="text"
          placeholder="Nome do PDF"
          name="nomePdf"
          value={formik.values.nomePdf}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
      </div>
      <div className="validacao">
        {formik.touched.nomePdf && formik.errors.nomePdf && (
          <div>{formik.errors.nomePdf}</div>
        )}
      </div>

      <div>
        <button type="submit" className="button-principal" disabled={loading}>
          {loading ? "Enviando..." : "Realizar o cadastro"}
        </button>
      </div>

      {message && (
        <div className={`message ${message.type === "success" ? "success" : "error"}`}>
          {message.text}
        </div>
      )}
    </form>
  );
};

export default Formulario;
