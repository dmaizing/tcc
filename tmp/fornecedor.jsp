<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@page import="java.text.SimpleDateFormat"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="cabecalho.jsp"/>


<form id="formFornecedor" class="formulario" method="get" action="Fornecedor">
    <c:choose>
        <c:when test="${param.acao eq 'novo'}">
            <h1>Novo Fornecedor</h1><br>                    

            <label for="for_nome">Nome..:</label>
            <input type="text" name="for_nome"  id="for_nome" required size="50" maxlength="50"/>
            <br />    
            <label for="for_fantasia">Nome Fantasia..:</label>
            <input type="text" name="for_fantasia"  id="for_fantasia" required size="30" maxlength="30"/>
            <br />
            <label for="log_codigo">Endereço..:</label>
            <input type="text" name="log_codigo"  id="log_codigo" size="1" maxlength="4" /> 
            <select name="selectLogradouroForn" id="selectLogradouroForn" onclick="atualizaCodigoLogradouroForn()" />
                <tagsTCC:listaLogradouroSelect/>
            </select> 
            <label for="for_numero">Número..:</label>
            <input type="text" name="for_numero"  id="for_numero" size="10" maxlength="10"/>
            <br />  
            
            <label for="bai_codigo">Bairro..:</label>
            <input type="text" name="bai_codigo"  id="bai_codigo" size="1" maxlength="4" /> 
            <select name="selectBairroForn" id="selectBairroForn" onclick="atualizaCodigoBairroForn()" />
                <tagsTCC:listaBairroSelect/>
            </select> 
            <label for="cid_codigo">Cidade..:</label>
            <input type="text" name="cid_codigo"  id="cid_codigo" size="1" maxlength="4" />
            <select name="selectCidade" id="selectCidade" onclick="atualizaCodigoCidadeForn()">
                <tagsTCC:listaCidadeSelect/>
            </select>
            <br />
            
            <label for="for_complemento">Complemento..:</label>
            <input type="text" name="for_complemento"  id="for_complemento" size="10" maxlength="10"/>
            <label for="for_cep">Cep..:</label>
            <input type="text" name="for_cep"  id="for_cep" size="9" maxlength="9" placeholder="99999-999" pattern="[0-9]{5}-[0-9]{3}"/>
            <br />
            
            <label for="for_cnpj">CNPJ..:</label>
            <input type="text" name="for_cnpj"  id="for_cnpj" size="18" maxlength="18"/>

            <label for="for_inscest">IE..:</label>
            <input type="text" name="for_inscest"  id="for_inscest" size="25" maxlength="25"/>
            <br />
            
            <label for="for_datacadastro">Data Cadastro..:</label>
            <input type="text"  name="for_datacadastro"  id="for_datacadastro" size="10" maxlength="10"/>
            <br />    
            <label for="for_fonecel">Fone Celular.:</label>
            <input type="tel" name="for_fonecel"  id="for_fonecel" size="16" maxlength="16"/>
            <br />    
            <label for="for_fone2">Fone outro..:</label>
            <input type="tel" name="for_fone2"  id="for_fone2"  size="16" maxlength="16"/>
            <br />
            <label for="for_email">Email.:</label>
            <input type="email" name="for_email"  id="for_email" size="50" maxlength="50"/>
            <br><br>
            <label for="for_obs">Obs..:</label>
            <input type="text" name="for_obs"  id="for_obs"  size="100" maxlength="100"/><br><br>
            <input type="submit" name="acao" value="novo"/>
            
        </form>    
            


        </c:when>
        <c:otherwise>
            <h1>Alteração de Fornecedor</h1>
            <br />
            <div id="formulario">
                <form id="formFornecedor" name="formFornecedor" method="post" action="Fornecedor">

                    <div style="float:left; width:70%;">
                        <label for="for_nome">Nome..:</label>
                        <input type="hidden" name="for_codigo"  id="for_codigo" value="${param.for_codigo}"/>
                        <input type="text" name="for_nome"  id="for_nome" required size="50" maxlength="50" value="${param.for_nome}"/>
                        <br />
                        
                        <label for="for_fantasia">Nome Fantasia..:</label>
                        <input type="text" name="for_fantasia"  id="for_fantasia" required size="30" maxlength="30" value="${param.for_fantasia}"/>
                        <br />
                        
                        <label for="log_codigo">Endereço..:</label>
                        <input type="text" name="log_codigo"  id="log_codigo" size="1" maxlength="4" value="${param.log_codigo}"/> 
                        <select name="selectLogradouroForn" id="selectLogradouroForn" onclick="atualizaCodigoLogradouroForn()" />
                            <tagsTCC:listaLogradouroSelect/>
                        </select>        
                        <label for="for_numero">Número..:</label>
                        <input type="text" name="for_numero"  id="for_numero" size="10" maxlength="10"  value="${param.for_numero}"/>
                        <br />
                        
                        <label for="bai_codigo">Bairro..:</label>
                        <input type="text" name="bai_codigo"  id="bai_codigo" size="1" maxlength="4"  value="${param.bai_codigo}"/>  
                        <select name="selectBairroForn" id="selectBairroForn" onclick="atualizaCodigoBairroForn() />
                            <tagsTCC:listaBairroSelect/>
                        </select> 

                        <label for="cid_codigo">Cidade..:</label>
                        <input type="text" name="cid_codigo"  id="cid_codigo" size="1" maxlength="4"  value="${param.cid_codigo}"/>
                        <select name="selectCidade" id="selectCidade" >
                            <tagsTCC:listaCidadeSelect/>
                        </select>
                        <br />


                        <label for="for_complemento">Complemento..:</label>
                        <input type="text" name="for_complemento"  id="for_complemento" size="10" maxlength="10"  value="${param.for_complemento}"/>
                        <label for="for_cep">Cep..:</label>
                        <input type="text" name="for_cep"  id="for_cep" size="10" maxlength="10"  value="${param.for_cep}"/>
                        <br />
                        
                        <label for="for_cnpj">CNPJ..:</label>
                        <input type="text" name="for_cnpj"  id="for_cnpj" size="18" maxlength="18"  value="${param.for_cnpj}"/>
                        <label for="clicpf">IE..:</label>
                        <input type="text" name="for_inscest" id="for_inscest"  size="25" maxlength="25" value="${param.for_inscest}"/>
                        <br />
                        
                        <label for="for_datacadastro">Data Cadastro..:</label>
                        <input type="text" name="for_datacadastro" id="for_datacadastro"  size="10" maxlength="10" value="${param.for_datacadastro}"/>

                        <label for="for_fonecel">Fone Celular.:</label>
                        <input type="text" name="for_fonecel"  id="for_fonecel" size="16" maxlength="16" value="${param.for_fonecel}"/>
                        <br />

                        <label for="for_fone2">Fone outro..:</label>
                        <input type="text" name="for_fone2"  id="for_fone2"  size="16" maxlength="16" value="${param.for_fone2}"/>
                        <br />
                        <label for="for_email">Email.:</label>
                        <input type="text" name="for_email"  id="for_email" size="50" maxlength="50" value="${param.for_email}"/>
                    </div>   

                    <div style="float:left;">
                        <label for="for_obs">Obs..:</label>
                        <input type="text" name="for_obs"  id="for_obs"  size="100" maxlength="100" value="${param.for_obs}"/><br><br>
                    </div>
                    <div style="float:left; clear:both;">
                        <input type="submit" name="acao" value="alterar"/>
                    </div>
                </form>

            </div>



        </c:otherwise>
    </c:choose>
<!--</form>-->
<c:import url="rodape.jsp"/>
