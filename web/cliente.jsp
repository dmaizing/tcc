<%-- 
    Videoaulas Neri www.informaticon.com.br   email:videoaulaneri@gmail.com
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="cabecalho.jsp"/>

<form id="formCliente" class="formulario" method="get" action="Cliente">
    <c:choose>
        <c:when test="${param.acao eq 'novo'}">
            <h1>Novo Cliente</h1><br>                    

            <label for="clinome">Nome..:</label>
            <input type="text" name="clinome"  id="clinome" required size="30" maxlength="30"/>

            <label for="logcodigo">Endereço..:</label>
            <input type="text" name="logcodigo"  id="logcodigo" size="1" maxlength="4" />                                
            <label for="baicodigo">Bairro..:</label>
            <input type="text" name="baicodigo"  id="baicodigo" size="1" maxlength="4" /> 
            <br><br>             
            <label for="cidcodigo">Cidade..:</label>
            <input type="text" name="cidcodigo"  id="cidcodigo" size="1" maxlength="4" />

            <label for="clinumero">Número..:</label>
            <input type="text" name="clinumero"  id="clinumero" size="10" maxlength="10"/>

            <label for="clicomplemento">Complemento..:</label>
            <input type="text" name="clicomplemento"  id="clicomplemento" size="10" maxlength="10"/>

            <label for="clicep">Cep..:</label>
            <input type="text" name="clicep"  id="clicep" size="9" maxlength="9" placeholder="99999-999" pattern="[0-9]{5}-[0-9]{3}"/>
            <br><br>
            <label for="clirg">RG..:</label>
            <input type="text" name="clirg"  id="clirg" size="20" maxlength="20"/>

            <label for="clicpf">CPF..:</label>
            <input type="text" onkeypress="formata_mascara(this, '###.###.###-##'); return Numero(event);"  name="clicpf"  id="clicpf" pattern="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}" placeholder="999.999.999-99" size="14" maxlength="14"/>

            <label for="clidatanasc">Data Nascimento..:</label>
            <input type="date" onkeypress="formata_mascara(this, '##/##/####'); return Numero(event);" placeholder="dd/mm/aaaa" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" name="clidatanasc"  id="clidatanasc" />

            <label for="clidatacadastro">Data Cadastro..:</label>
            <input type="date" onkeypress="formata_mascara(this, '##/##/####'); return Numero(event);" placeholder="dd/mm/aaaa" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" name="clidatacadastro"  id="clidatacadastro"  size="10" maxlength="10"/>
            <br />    
            <label for="clifonecel">Fone Celular.:</label>
            <!--        <input type="tel"  pattern="\(0xx[0-9]{2}\)[0-9]{4}-[0-9]{4}" placeholder="(0xx99)9999-9999" name="clifonecel"  id="clifonecel" size="16" maxlength="16"/>-->
            <input type="tel" name="clifonecel"  id="clifonecel" size="16" maxlength="16"/>
            <br />    
            <label for="clifone2">Fone outro..:</label>
            <!--<input type="tel" pattern="\(0xx[0-9]{2}\)[0-9]{4}-[0-9]{4}"  placeholder="(0xx99)9999-9999" name="clifone2"  id="clifone2"  size="16" maxlength="16"/>-->
            <input type="tel" name="clifone2"  id="clifone2"  size="16" maxlength="16"/>
            <br />
            <label for="cliemail">Email.:</label>
            <input type="email" name="cliemail"  id="cliemail" size="50" maxlength="50"/>
            <div id="foto">
                <img  alt="Foto" width="100" height="100" name="mostraFoto" id="mostraFoto"/>
            </div>

            <label>Sexo..:  Maculino
                <input type="radio" name="clisexo"  id="clisexo" value="M" checked="checked"/>
            </label>
            <label>Feminino
                <input type="radio" name="clisexo"  id="clisexo" value="F"/> 
            </label>
            <br><br>
            <label for="clifoto">Foto..:</label>
            <input type="text" name="clifoto"  id="clifoto"  size="40" maxlength="40"/>
            <input type="file" name="pegaFoto"  id="pegaFoto"  />

            <br><br>

            <label for="cliobs">Obs..:</label>
            <input type="text" name="cliobs"  id="cliobs"  size="100" maxlength="100"/><br><br>
            <input type="submit" name="acao" value="novo"/>


            


        </c:when>
        <c:otherwise>
            <h1>Alteração de Cliente</h1>
            <br />

            <!--            <br>                     
                        <label for="clinome">Nome..:</label>
                        <input type="hidden" name="clicodigo"  id="clicodigo" value="${param.clicodigo}"/>
                        <input type="text" name="clinome"  id="clinome" required size="30" maxlength="30" value="${param.clinome}"/>
            
                        <label for="logcodigo">Endereço..:</label>
                        <input type="text" name="logcodigo"  id="logcodigo" size="1" maxlength="4" value="${param.logcodigo}"/> 
                        <select name="selectLogradouro" id="selectLogradouro">
            <tagsTCC:listaLogradouroSelect/>
        </select>                               

        <label for="baicodigo">Bairro..:</label>
        <input type="text" name="baicodigo"  id="baicodigo" size="1" maxlength="4"  value="${param.baicodigo}"/>  
        <select name="selectBairro" id="selectBairro">
            <tagsTCC:listaBairroSelect/>
        </select> 
        <br><br> 

        <label for="cidcodigo">Cidade..:</label>
        <input type="text" name="cidcodigo"  id="cidcodigo" size="1" maxlength="4"  value="${param.cidcodigo}"/>
        <select name="selectCidade" id="selectCidade" >
            <tagsTCC:listaCidadeSelect/>
        </select>

        <label for="clinumero">Número..:</label>
        <input type="text" name="clinumero"  id="clinumero" size="10" maxlength="10"  value="${param.clinumero}"/>

        <label for="clicomplemento">Complemento..:</label>
        <input type="text" name="clicomplemento"  id="clicomplemento" size="10" maxlength="10"  value="${param.clicomplemento}"/>

        <label for="clicep">Cep..:</label>
        <input type="text" name="clicep"  id="clicep" size="10" maxlength="10"  value="${param.clicep}"/>
        <br><br>

        <label for="clirg">RG..:</label>
        <input type="text" name="clirg"  id="clirg" size="20" maxlength="20"  value="${param.clirg}"/>

        <label for="clicpf">CPF..:</label>
        <input type="text" name="clicpf" onkeypress="formata_mascara(this, '###.###.###-##'); return Numero(event);" id="clicpf"  size="14" maxlength="14" value="${param.clicpf}"/>

        <label for="clidatanasc">Data Nascimento..:</label>
        <input type="date" onkeypress="formata_mascara(this, '##/##/####'); return Numero(event);" name="clidatanasc"    id="clidatanasc" size="10" maxlength="10" value="${param.clidatanasc}"/>
        <br><br>

        <label for="clidatacadastro">Data Cadastro..:</label>
        <input type="date" onkeypress="formata_mascara(this, '##/##/####'); return Numero(event);" name="clidatacadastro" id="clidatacadastro"  size="10" maxlength="10" value="${param.clidatacadastro}"/>

        <label for="clifonecel">Fone Celular.:</label>
        <input type="text" name="clifonecel"  id="clifonecel" size="16" maxlength="16" value="${param.clifonecel}"/>

        <label for="clifone2">Fone outro..:</label>
        <input type="text" name="clifone2"  id="clifone2"  size="16" maxlength="16" value="${param.clifone2}"/>
        <br><br>
        <label for="cliemail">Email.:</label>
        <input type="text" name="cliemail"  id="cliemail" size="50" maxlength="50" value="${param.cliemail}"/>


        <div id="foto">
            <img  alt="Foto" width="100" height="100" name="mostraFoto" id="mostraFoto" src="imagens/${param.clifoto }"/>
        </div>

        <label>Sexo..:  Maculino
            <input type="radio" name="clisexo"  id="clisexo" value="M" <tagsTCC:sexomasculino/> />
        </label>
        <label>Feminino
            <input type="radio" name="clisexo"  id="clisexo" value="F" <tagsTCC:sexofeminino/>	 /><br><br> 
        </label>
        <label for="clifoto">Foto..:</label>
        <input type="text" name="clifoto"  id="clifoto"  size="40" maxlength="40" value="${param.clifoto}"/>
        <input type="file" name="pegaFoto"  id="pegaFoto"  />
        <br><br>
        <label for="cliobs">Obs..:</label>
        <input type="text" name="cliobs"  id="cliobs"  size="100" maxlength="100" value="${param.cliobs}"/><br><br>
        <input type="submit" name="acao" value="alterar"/>-->

            <div id="formulario">

                <form id="form1" name="form1" method="post" action="Cliente">

                    <div style="float:left; width:70%;">
                        <label for="clinome">Nome..:</label>
                        <input type="hidden" name="clicodigo"  id="clicodigo" value="${param.clicodigo}"/>
                        <input type="text" name="clinome"  id="clinome" required size="30" maxlength="30" value="${param.clinome}"/>
                    
                        <label for="logcodigo">Endereço..:</label>
                        <input type="text" name="logcodigo"  id="logcodigo" size="1" maxlength="4" value="${param.logcodigo}"/> 
<!--                        <select name="selectLogradouro" id="selectLogradouro">
                            <tagsTCC:listaLogradouroSelect/>
                        </select>                               -->
                        <br />

                        <label for="baicodigo">Bairro..:</label>
                        <input type="text" name="baicodigo"  id="baicodigo" size="1" maxlength="4"  value="${param.baicodigo}"/>  
<!--                        <select name="selectBairro" id="selectBairro">
                            <tagsTCC:listaBairroSelect/>
                        </select> -->

                        <label for="cidcodigo">Cidade..:</label>
                        <input type="text" name="cidcodigo"  id="cidcodigo" size="1" maxlength="4"  value="${param.cidcodigo}"/>
<!--                        <select name="selectCidade" id="selectCidade" >
                            <tagsTCC:listaCidadeSelect/>
                        </select>-->
                        <br />

                        <label for="clinumero">Número..:</label>
                        <input type="text" name="clinumero"  id="clinumero" size="10" maxlength="10"  value="${param.clinumero}"/>

                        <label for="clicomplemento">Complemento..:</label>
                        <input type="text" name="clicomplemento"  id="clicomplemento" size="10" maxlength="10"  value="${param.clicomplemento}"/>
                        <br />    

                        <label for="clicep">Cep..:</label>
                        <input type="text" name="clicep"  id="clicep" size="10" maxlength="10"  value="${param.clicep}"/>
                        <label for="clirg">RG..:</label>
                        <input type="text" name="clirg"  id="clirg" size="20" maxlength="20"  value="${param.clirg}"/>
                        <label for="clicpf">CPF..:</label>
                        <input type="text" name="clicpf" onkeypress="" id="clicpf"  size="14" maxlength="14" value="${param.clicpf}"/>
                        <br />

                        <label for="clidatanasc">Data Nascimento..:</label>
                        <!--<input type="date" onkeypress="formata_mascara(this, '##/##/####'); return Numero(event);" name="clidatanasc"    id="clidatanasc" size="10" maxlength="10" value="${param.clidatanasc}"/>-->
                        <input type="text" name="clidatanasc" id="clidatanasc" size="10" maxlength="10" value="${param.clidatanasc}"/>
                        <br><br>

                        <label for="clidatacadastro">Data Cadastro..:</label>
                        <!--<input type="date" onkeypress="formata_mascara(this, '##/##/####'); return Numero(event);" name="clidatacadastro" id="clidatacadastro"  size="10" maxlength="10" value="${param.clidatacadastro}"/>-->
                        <input type="text" name="clidatacadastro" id="clidatacadastro"  size="10" maxlength="10" value="${param.clidatacadastro}"/>

                        <label for="clifonecel">Fone Celular.:</label>
                        <input type="text" name="clifonecel"  id="clifonecel" size="16" maxlength="16" value="${param.clifonecel}"/>
                        <br />

                        <label for="clifone2">Fone outro..:</label>
                        <input type="text" name="clifone2"  id="clifone2"  size="16" maxlength="16" value="${param.clifone2}"/>
                        <br />
                        <label for="cliemail">Email.:</label>
                        <input type="text" name="cliemail"  id="cliemail" size="50" maxlength="50" value="${param.cliemail}"/>
                    </div>   

                    <div style="float:right; width:30%;">
                        <img  alt="Foto" width="100" height="100" name="mostraFoto" id="mostraFoto" src="imagens/${param.clifoto }"/>
                    </div>

                    <label>Sexo..:  Maculino
                        <input type="radio" name="clisexo"  id="clisexo" value="M" <tagsTCC:sexomasculino/> />
                    </label>
                    <label>Feminino
                        <input type="radio" name="clisexo"  id="clisexo" value="F" <tagsTCC:sexofeminino/> /><br><br> 
                    </label>

                    <div style="float:left;">
                        <label for="cliobs">Obs..:</label>
                        <input type="text" name="cliobs"  id="cliobs"  size="100" maxlength="100" value="${param.cliobs}"/><br><br>
                    </div>

                    <div style="float:left; clear:both;">
                        <label for="clifoto">Foto..:</label>
                        <input type="text" name="clifoto"  id="clifoto"  size="40" maxlength="40" value=""/>
                        <input type="file" name="pegaFoto"  id="pegaFoto"  />
                    </div>

                    <div style="float:left; clear:both;">
                        <input type="submit" name="acao" value="alterar"/>
                    </div>
                </form>

            </div>



        </c:otherwise>
    </c:choose>
</form>
<c:import url="rodape.jsp"/>
