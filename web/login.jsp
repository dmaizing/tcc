<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
<%@taglib tagdir="/WEB-INF/tags"  prefix="tagsTCC" %>

<div id="login">
    <form action="Logar" method="get" class="frmLogin">
        <table class="table_login" border="1">
            <tr>
                <td colspan="2"><h1>Acesso ao Sistema</h1></td>
            </tr>
            <tr>
                <td>Usuário.:</td>
                <td><input type="text" autofocus="true" placeholder="usuário" autocomplete="on" required="true" name="usuario" /></td>

            </tr>
            <tr>
                <td>Senha.:</td>
                <td><input type="password" placeholder="senha" name="senha" /></td>

            </tr>
            <tr>
                <td><input type="reset" value="Limpar" /></td>
                <td><input type="submit" name="acessar" value="Acessar" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <h2>
                        <tagsTCC:statusUsuarioSenha />
                    </h2>
                </td>
            </tr>
        </table>
    </form>
</div>                        

<c:import url="rodape.jsp" />