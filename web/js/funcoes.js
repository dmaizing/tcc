/* 
 * Prof Neri Neitzke - Mais de 100 palestras em 7 paÃ­ses
 * Videoaulas Neri wwww.informaticon.com.br
 * email: videoaulaneri@gmail.com
 */
function validarSenha(input) {
    if (input.value !== document.getElementById("senha").value) {
        input.setCustomValidity('Repita a senha');
    } else {
        input.setCustomValidity('');
    }
}

//função para a criação de máscaras nos campos
function formata_mascara(campo_passado, mascara) 
{
	var campo = campo_passado.value.length;
	var saida = mascara.substring(0,1);
	var texto = mascara.substring(campo);
	if(texto.substring(0,1) != saida) 
	{
		campo_passado.value += texto.substring(0,1);
    }			
}

function Numero(e)
{
	var tecla;
	navegador = /msie/i.test(navigator.userAgent);
	if (navegador)
		tecla = event.keyCode;
	else
		tecla = e.which;
	
	if(tecla > 47 && tecla < 58) // numeros de 0 a 9
		return true;
	else
		{
			if (tecla != 8) // backspace
				return false;
			else
				return true;
		}
	}
function atualizarFoto() {
	var fotoDigitada = document.forms['formCliente']['clifoto'].value;
	document.forms['formCliente']['mostraFoto'].src = "imagens/"+fotoDigitada;				
}
function fileFoto() {
	var pegaFileFoto = document.forms['formCliente']['pegaFoto'].value;
	document.forms['formCliente']['clifoto'].value =pegaFileFoto;
	atualizarFoto();
}

function atualizaCodigoLogradouro() {
	var getLogCodigo = document.forms['formCliente']['selectLogradouro'].value;
	document.forms['formCliente']['logcodigo'].value =getLogCodigo;
}
function atualizaCodigoBairro() {
	var getBaiCodigo = document.forms['formCliente']['selectBairro'].value;
	document.forms['formCliente']['baicodigo'].value =getBaiCodigo;
}

function atualizaCodigoCidade() {
	var getCidCodigo = document.forms['formCliente']['selectCidade'].value;
	document.forms['formCliente']['cidcodigo'].value =getCidCodigo;
}
window.onload = function() {
	document.forms['formCliente']['clifoto'].oninput = atualizarFoto;
	document.forms['formCliente']['pegaFoto'].onblur = fileFoto;
	document.forms['formCliente']['selectLogradouro'].onclick = atualizaCodigoLogradouro;
	document.forms['formCliente']['selectBairro'].onclick = atualizaCodigoBairro;
	document.forms['formCliente']['selectCidade'].onclick = atualizaCodigoCidade;
	
};


