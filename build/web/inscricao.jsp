<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Inscrição Evento</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            border-radius: 15px;
            padding: 40px 30px;
            width: 100%;
            max-width: 650px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 10px;
        }
        .intro {
            text-align: center;
            color: #555;
            margin-bottom: 25px;
        }
        .mensagem {
            text-align: center;
            margin-bottom: 20px;
            font-size: 16px;
            padding: 10px;
            border-radius: 8px;
        }
        .mensagem.sucesso { background-color: #d4edda; color: #155724; }
        .mensagem.erro { background-color: #f8d7da; color: #721c24; }

        form label { display: block; margin-top: 15px; font-weight: 600; color: #333; }
        form input, form select, form textarea {
            width: 100%; padding: 12px; margin-top: 5px; border-radius: 8px;
            border: 1px solid #ccc; font-size: 14px;
        }
        form input:focus, form select:focus, form textarea:focus {
            border-color: #2575fc; box-shadow: 0 0 5px rgba(37,117,252,0.5); outline: none;
        }
        textarea { resize: vertical; min-height: 80px; }
        button {
            margin-top: 25px; width: 100%; padding: 15px;
            background-color: #2575fc; color: white; border: none;
            font-size: 16px; font-weight: bold; border-radius: 8px; cursor: pointer;
            transition: 0.3s;
        }
        button:hover { background-color: #6a11cb; }
    </style>
</head>
<body>
<div class="container">
    <h1>Inscrição no Evento</h1>
    <p class="intro">Escolha a área de interesse que mais combina com você e participe!</p>

    <%
        String mensagem = (String) request.getAttribute("mensagem");
        String tipo = (String) request.getAttribute("tipoMensagem");
        if (mensagem != null) {
    %>
        <div class="mensagem <%= tipo %>"><%= mensagem %></div>
    <% } %>

    <form action="InscricaoServlet" method="post">
        <label for="nome">Nome completo</label>
        <input type="text" id="nome" name="nome" required>

        <label for="email">E-mail</label>
        <input type="email" id="email" name="email" required>

        <label for="telefone">Telefone</label>
        <input type="text" id="telefone" name="telefone" required>

        <label for="idade">Idade</label>
        <input type="number" id="idade" name="idade" required>

        <label for="genero">Gênero</label>
        <select id="genero" name="genero" required>
            <option value="">Selecione</option>
            <option value="Masculino">Masculino</option>
            <option value="Feminino">Feminino</option>
            <option value="Outro">Outro</option>
        </select>

        <label for="cidade">Cidade</label>
        <input type="text" id="cidade" name="cidade" required>

        <label for="estado">Estado</label>
        <input type="text" id="estado" name="estado" required>

        <label for="profissao">Profissão</label>
        <input type="text" id="profissao" name="profissao">

        <label for="interesse">Área de Interesse</label>
        <input type="text" id="interesse" name="interesse" required>

        <label for="observacoes">Observações</label>
        <textarea id="observacoes" name="observacoes"></textarea>

        <button type="submit">Enviar Inscrição</button>
    </form>
</div>
</body>
</html>
