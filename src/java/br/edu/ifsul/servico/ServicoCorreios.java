/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.servico;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.tempuri.CResultado;
import org.tempuri.CalcPrecoPrazoWS;

/**
 *
 * @author Rafael
 */
@Stateless
@Path("servicocorreios")
@ApplicationPath("servicos")
public class ServicoCorreios extends Application implements Serializable {

    private Gson gson = new Gson();
    private CalcPrecoPrazoWS servico;

    @POST
    @Consumes("application/json; charset=ISO-8859-1")
    @Produces("application/json; charset=ISO-8859-1")

    public Response calculaCorreio(Correio objeto) {
        servico = new CalcPrecoPrazoWS();
        try {
            if (objeto.getCepDestino()== null) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            CResultado resultado = servico.getCalcPrecoPrazoWSSoap()
                    .calcPrecoPrazo("", "", objeto.getCodigoServico(),
                            "99030010",
                            objeto.getCepDestino(),
                            objeto.getPeso(),
                            1,
                            new BigDecimal(30),
                            new BigDecimal(30),
                            new BigDecimal(30),
                            new BigDecimal(0.0),
                            "s",
                            new BigDecimal(0.0),
                            "s");

            if (!resultado.getServicos().getCServico().get(0).getMsgErro().isEmpty()) {
                String res = resultado.getServicos().getCServico().get(0).getMsgErro();
                System.out.println(""+res);
                if (res.equals("CEP de origem invalido.")) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                } else if (res.equals("CEP de destino invalido.")) {
                    return Response.status(Response.Status.BAD_GATEWAY).build();
                }else if (res.equals("O comprimento nao pode ser inferior a 16 cm.")) {
                    return Response.status(Response.Status.EXPECTATION_FAILED).build();
                }else if (res.equals("Erro ao calcular tarifa no SGPB. ERP-031: Nao informado o peso, a quantidade ou o valor(-1).")) {
                    return Response.status(Response.Status.CONFLICT).build();
                }
                else if (res.equals("A largura nao pode ser inferior a 11 cm.")) {
                    return Response.status(Response.Status.CREATED).build();
                }
                else if (res.equals("A altura nao pode ser inferior a 2 cm.")) {
                    return Response.status(Response.Status.FORBIDDEN).build();
                }
                else if (res.equals("Peso excedido.")) {
                    return Response.status(Response.Status.GONE).build();
                
                }  else if (res.equals("O comprimento nao pode ser maior que 105 cm.")) {
                    return Response.status(Response.Status.MOVED_PERMANENTLY).build();
     
                } else if (res.equals("A largura nao pode ser maior que 105 cm.")) {
                    return Response.status(Response.Status.NO_CONTENT).build();   
                }else if (res.equals("A altura nao pode ser maior que 105 cm.")) {
                    return Response.status(Response.Status.PAYMENT_REQUIRED).build();   
                }else if (res.equals("A soma resultante do comprimento + largura + altura nao deve superar a 200 cm.")) {
                    return Response.status(Response.Status.HTTP_VERSION_NOT_SUPPORTED).build();   
                }else if (res.equals("Serviço indisponível para o trecho informado.")) {
                    return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();   
                }
                
   
                else{
                    return Response.status(Response.Status.PRECONDITION_FAILED).build();
                }

            } else {

                objeto.setValorFrete(Double.parseDouble(resultado.getServicos().getCServico().get(0).getValor().replace(",", ".")));
                objeto.setPrazoEntrega(Integer.parseInt(resultado.getServicos().getCServico().get(0).getPrazoEntrega()));

            }

        } catch (NumberFormatException e) {
            System.out.println("" + e);
        }

        return Response.ok(gson.toJson(objeto)).build();
    }
    
    
  
//{"codigoServico":"04014","cepDestino":"99030040","peso":"1"}

}
