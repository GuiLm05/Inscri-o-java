package InscricaoServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "InscricaoServlet", urlPatterns = {"/InscricaoServlet"})
public class InscricaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recebendo dados do formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String idade = request.getParameter("idade");
        String genero = request.getParameter("genero");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String profissao = request.getParameter("profissao");
        String interesse = request.getParameter("interesse");
        String observacoes = request.getParameter("observacoes");

        String mensagem;
        String tipoMensagem;

        // Salvar no banco de dados
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evento", "root", "");
                 PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO inscricoes (nome,email,telefone,idade,genero,cidade,estado,profissao,interesse,observacoes) VALUES (?,?,?,?,?,?,?,?,?,?)")) {

                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setString(3, telefone);
                ps.setString(4, idade);
                ps.setString(5, genero);
                ps.setString(6, cidade);
                ps.setString(7, estado);
                ps.setString(8, profissao);
                ps.setString(9, interesse);
                ps.setString(10, observacoes);
                ps.executeUpdate();

                mensagem = "✅ Inscrição realizada com sucesso!";
                tipoMensagem = "sucesso";
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "❌ Erro ao salvar inscrição: " + e.getMessage();
            tipoMensagem = "erro";
        }

        // Enviar mensagem de volta ao JSP
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        request.getRequestDispatcher("inscricao.jsp").forward(request, response);
    }
}
