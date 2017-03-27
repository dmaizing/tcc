<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@page import="java.text.SimpleDateFormat"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="cabecalho.jsp"/>


    <c:choose>
        <c:when test="${param.acao eq 'novo'}">
            
            <div id="formulario">

                <form id="formProduto" class="formulario" method="get" action="Produto">
                    <h1>Novo Produto</h1><br>                    

                    <div style="float:left; width:70%;">
                        <label for="catcodigo">Categoria..:</label>
                        <input type="text" name="catcodigo"  id="catcodigo" size="1" maxlength="4" />                                
                        <select name="selectCategoria" id="selectCategoria" onclick="atualizaCodigoCategoriaProd()">
                            <tagsTCC:listaCategoriaSelect/>
                        </select> 
                        <br />    
                        <label for="for_codigo">Fornecedor..:</label>
                        <input type="text" name="for_codigo"  id="for_codigo" size="1" maxlength="4" /> 
                        <select name="selectFornecedor" id="selectFornecedor" onclick="atualizaCodigoFornecedorProd()">
                            <tagsTCC:listaFornecedorSelect />
                        </select> 
                        <br />
                        
                        <label for="prodescricao">Descrição..:</label>
                        <input type="text" name="prodescricao"  id="prodescricao" required size="60" maxlength="60"/>
                        <br /> 
                        
                        <label for="proqtdestoque">QTD ESTOQUE..:</label>
                        <input type="text" name="proqtdestoque"  id="proqtdestoque" size="15" maxlength="15"/>
                        <br />
                        
                        <label for="proprecocusto">R$ PREÇO CUSTO..:</label>
                        <input type="text" name="proprecocusto"  id="proprecocusto" size="15" maxlength="15"/>
                        <br />
                        
                        <label for="properclucro">%PERC. LUCRO..:</label>
                        <input type="text" name="properclucro"  id="properclucro" size="4" maxlength="4"/>
                        <br />
                        
                        <label for="proprecovenda">R$ PREÇO VENDA..:</label>
                        <input type="text" name="proprecovenda"  id="proprecovenda" size="15" maxlength="15"/>
                        <br />
                        
                        <label for="prodatacadastro">Data Cadastro..:</label>
                        <input type="text"  name="prodatacadastro"  id="prodatacadastro"  size="10" maxlength="10"/>
                        
                    </div>
                    
                    <br><br>
                    <div style="float:left;">
                        <label for="pro_obs">Obs..:</label>
                        <input type="text" name="pro_obs"  id="pro_obs"  size="100" maxlength="100" /><br><br>
                    </div> 
                    
                    <div style="float:left; clear:both;">
                        <input type="submit" name="acao" value="novo"/>
                    </div>    
                </form>    

            </div>  

    </c:when>
    <c:otherwise>
        <h1>Alteração de Produto</h1>
        <br />

        <div id="formulario">

            <form id="formProduto" class="formulario" method="get" action="Produto">
                    <h1>Novo Produto</h1><br>                    

                    <div style="float:left; width:70%;">
                        <label for="catcodigo">Categoria..:</label>
                        <input type="hidden" name="procodigo"  id="procodigo" value="${param.procodigo}"/>
                        
                        <input type="text" name="catcodigo"  id="catcodigo" size="1" maxlength="4" value="${param.catcodigo}" />                                
                        <select name="selectCategoria" id="selectCategoria" onclick="atualizaCodigoCategoriaProd()">
                            <tagsTCC:listaCategoriaSelect/>
                        </select> 
                        <br />    
                        <label for="for_codigo">Fornecedor..:</label>
                        <input type="text" name="for_codigo"  id="for_codigo" size="1" maxlength="4" value="${param.for_codigo}" /> 
                        <select name="selectFornecedor" id="selectFornecedor" onclick="atualizaCodigoFornecedorProd()">
                            <tagsTCC:listaFornecedorSelect />
                        </select> 
                        <br />
                        
                        <label for="prodescricao">Descrição..:</label>
                        <input type="text" name="prodescricao"  id="prodescricao" required size="60" maxlength="60" value="${param.prodescricao}"/>
                        <br /> 
                        
                        <label for="proqtdestoque">QTD ESTOQUE..:</label>
                        <input type="text" name="proqtdestoque"  id="proqtdestoque" size="15" maxlength="15" value="${param.proqtdestoque}"/>
                        <br />
                        
                        <label for="proprecocusto">R$ PREÇO CUSTO..:</label>
                        <input type="text" name="proprecocusto"  id="proprecocusto" size="15" maxlength="15" value="${param.proprecocusto}"/>
                        <br />
                        
                        <label for="properclucro">%PERC. LUCRO..:</label>
                        <input type="text" name="properclucro"  id="properclucro" size="4" maxlength="4" value="${param.properclucro}"/>
                        <br />
                        
                        <label for="proprecovenda">R$ PREÇO VENDA..:</label>
                        <input type="text" name="proprecovenda"  id="proprecovenda" size="15" maxlength="15" value="${param.proprecovenda}"/>
                        <br />
                        
                        <label for="prodatacadastro">Data Cadastro..:</label>
                        <input type="text"  name="prodatacadastro"  id="prodatacadastro"  size="10" maxlength="10" value="${param.prodatacadastro}"/>
                        <br />
                        <label for="prodatacadastro">Data Ultima Venda..:</label>
                        <input type="text"  name="prodataultvenda"  id="prodataultvenda"  size="10" maxlength="10" value="${param.prodataultvenda}"/>
                        
                    </div>
                    
                    <br><br>
                    <div style="float:left;">
                        <label for="pro_obs">Obs..:</label>
                        <input type="text" name="pro_obs"  id="pro_obs"  size="100" maxlength="100" value="${param.pro_obs}" /><br /><br />
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
