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
    private String nome; 
    private String email;
    private String telefone; 
    private int idade; 
    private String genero; 
    private String cidade; 
    private String estado; 
    private String profissao; 
    private String interesse; 
    private String observacao; 

    private String mensagem;
    private String tipoMensagem;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        nome = request.getParameter("nome");
        email = request.getParameter("email");
        telefone = request.getParameter("telefone");
        String idadeStr = request.getParameter("idade");
        if (idadeStr != null && !idadeStr.isEmpty()) {
            idade = Integer.parseInt(idadeStr);
        } else {
            idade = 0; // valor padrão caso o campo esteja vazio
        }
        genero = request.getParameter("genero");
        cidade = request.getParameter("cidade");
        estado = request.getParameter("estado");
        profissao = request.getParameter("profissao");
        interesse = request.getParameter("interesse");
        observacao = request.getParameter("observacao");
        
        // Salvar no banco de dados
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ATV-1", "root", "SENHA DO BANCO DE DAODS");
                 PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO inscricao (nome,email,telefone,idade,genero,cidade,estado,profissao,interesse,observacao) VALUES (?,?,?,?,?,?,?,?,?,?)")) {

                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setString(3, telefone);
                ps.setInt(4, idade);
                ps.setString(5, genero);
                ps.setString(6, cidade);
                ps.setString(7, estado);
                ps.setString(8, profissao);
                ps.setString(9, interesse);
                ps.setString(10, observacao);
                ps.executeUpdate();

                mensagem = " Inscrição realizada com sucesso!";
                tipoMensagem = "sucesso";
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = " Erro ao salvar inscrição: " + e.getMessage();
            tipoMensagem = "erro";
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        request.getRequestDispatcher("inscricao.jsp").forward(request, response);
    }
}
