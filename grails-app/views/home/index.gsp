<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <g:set var="avaliacao" value="${message(code: 'avaliacao.label')}"/>
        <g:set var="tecnologia" value="${message(code: 'tecnologia.label')}"/>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <title><g:message code="window.title"/></title>
        <script type="text/javascript">

            function grauParaPi(grau) {
                return (Math.PI / 180) * (360 - grau)
            }

            function cancel(e) {
                if (e.preventDefault) {
                    e.preventDefault();
                }
                return false;
            }

            function handleDragStart(e) {
                // document.getElementById('texto').value = e.target.style.top;
            }

            function handleDragOver(e) {
                //document.getElementById('texto').value = 'dragOver';
                e.preventDefault();
                return false;
            }

            function handleDragEnd(e) {
                document.getElementById('texto').value = 'fpos: ' + e.clientX + ',' + e.clientY + ', tag=' + e.target.tagName;
                e.target.src = './yellow.gif';
                e.target.setPosition(500, 25);
                e.preventDefault();
                return false;
            }

            function doRadar() {
                var canvas = document.getElementById('cvRadar');

                // Make sure we don't execute when canvas isn't supported
                if (canvas.getContext) {

                    // use getContext to use the canvas for drawing
                    var ctx = canvas.getContext('2d');

                    // default stuff
                    ctx.font = "16px Arial";

                    // reta X
                    ctx.strokeStyle = "rgb(176,176,176)";
                    ctx.beginPath();
                    ctx.moveTo(canvas.width / 2, 10);
                    ctx.lineTo(canvas.width / 2, canvas.height - 10);
                    ctx.stroke();
                    ctx.closePath();

                    // reta Y
                    ctx.strokeStyle = "rgb(176,176,176)";
                    ctx.beginPath();
                    ctx.moveTo(10, canvas.height / 2);
                    ctx.lineTo(canvas.width - 10, canvas.height / 2);
                    ctx.stroke();
                    ctx.closePath();

                    // circulo Adopt
                    ctx.strokeStyle = "rgb(119,212,159)";
                    ctx.beginPath();
                    ctx.lineWidth = 3;
                    // draw arc: arc(x, y, radius, startAngle, endAngle, anticlockwise)
                    ctx.arc(canvas.width / 2, canvas.height / 2, 100, grauParaPi(0), grauParaPi(360), true);
                    ctx.stroke();
                    ctx.closePath();

                    // circulo Adopt
                    ctx.strokeStyle = "rgb(200,0,0)";
                    ctx.beginPath();
                    ctx.lineWidth = 3;
                    ctx.arc(canvas.width / 2, canvas.height / 2, 280, grauParaPi(0), grauParaPi(360), true);
                    ctx.stroke();
                    ctx.closePath();

                    // textos
                    ctx.fillStyle = "rgb(119,212,159)";
                    ctx.fillText("Adopt", (canvas.width / 2) + 90, (canvas.height / 2) + 70);

                    ctx.fillStyle = "rgb(200,0,0)";
                    ctx.fillText("Discontinue", (canvas.width / 2) + 70, 20);
                }
                else {
                    alert('You need Safari or Firefox 1.5+ to see this demo.');
                }
            }
        </script>

        <style>
            .ponto{
                border: 1px solid green;
                width: 32px; height: 32px;
                position:absolute;
                left:100px;
                top:100px;
            }
        </style>
    </head>

    <body onload="doRadar();">

        <article>
            <table width="800">
                <tr>
                    <td width="50%"><span style="font-family: Arial; font-size: 36px; font-weight: bold; ">CDA Tech Radar</span></td>
                    <td align="right">
                        <g:select name="avaliacoes" from="${avaliacoes}" noSelection="['':'']" />
                        <input type="button" value="Filtrar" />
                        <input id="texto" type="text"/>
                    </td>
                </tr>
            </table>
            <canvas id="cvRadar" width="800" height="600" style="border: 1px solid black;">
                <p> Seu Navegador nao da suporte ao canvas</p>
            </canvas>

            <g:each in="${tecnologias}" var="tech">
                <img src="${request.contextPath}/images/dot.gif" style="position:absolute; left:${tech.x}px; top:${tech.y}px;" alt="${tech.nome}" title="${tech.nome}"/>
            </g:each>
        </article>
        <aside>
            <ul style="position: absolute; left: 820px; top: 100px;">
                <li><g:link class="create" controller="avaliacao" action="create"><g:message code="default.new.label" args="[avaliacao]" /></g:link></li>
                <li><g:link class="create" controller="tecnologia" action="create"><g:message code="default.new.label" args="[tecnologia]" /></g:link></li>
            </ul>
        </aside>
    </body>

</html>
