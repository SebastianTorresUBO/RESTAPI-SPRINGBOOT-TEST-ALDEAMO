package springboot.restapitest.RestController;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import springboot.restapitest.model.ArrayRespuesta;
import springboot.restapitest.model.ArraysEntity;
import springboot.restapitest.model.ErrorObject;
import springboot.restapitest.service.ArraysService;

@RestController
@RequestMapping("/arrays")
public class ArraysController {
	
	@Autowired
	ArraysService arraysService;
	
	private ArrayList<Integer> numPrimos=new ArrayList<Integer>();
	private ArrayList<Integer> B=new ArrayList<Integer>();
	private ArrayList<Integer> A=new ArrayList<Integer>();
	private ArrayList<Integer> respuesta=new ArrayList<Integer>();
	
	
	@Operation(
			summary = "Consulta de un input_array en particular",
			description = "Consulta de array especifico por medio del ID, también debe ser informado el ID de Q para la cantidad de iteraciones a realizar, retorna el ARRAY RESULTADO ",
			tags = "Consulta Input_array especifico y retorna Array de resultado"
	)
	@ApiResponses(
	 value= {
			 @ApiResponse(
					 responseCode = "200",
					 description = "successful operation",
					 content= @Content(
							 array= @ArraySchema(
									 schema=@Schema( implementation = ArrayRespuesta.class)
							 )
					 )
			 ),
			 @ApiResponse(
					 responseCode = "204",
					 description = "No content, No existe el ID del input solicitado ",
					 content= @Content(
							 schema = @Schema(implementation = ErrorObject.class)
							 )
			 )
			 
	 }
	)
	@CrossOrigin(origins="*")
	@GetMapping("/array/{id}/{q}")
	public ResponseEntity<ArrayRespuesta> tomarArregloVasoPorID(@PathVariable(value="id") Long id, @PathVariable(value="q") Long qIteraciones){

		ArraysEntity arrays= arraysService.buscarVasosPorID(id);
		if(arrays==null) {
			return ResponseEntity.noContent().build();
		}
		ArrayRespuesta arr = doLogic(arrays, qIteraciones);
		return ResponseEntity.ok().body(arr);
	}
	
	public ArrayRespuesta doLogic(ArraysEntity arrays, Long qIteraciones) {
		ArrayRespuesta arr= new ArrayRespuesta();
		try {
			ArrayList<Integer> output=new ArrayList<Integer>();
			llenarArrayNumerosPrimos(100000);
			String[] arrayVasos=convertVasosStringtoArray(arrays.getInput_array());
			output =retirarVaso(arrayVasos, this.numPrimos, qIteraciones.intValue());
			
			arr.setArrayResult(output);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public ArrayList<Integer> retirarVaso(String[] arrayVasos, ArrayList<Integer> numerosPrimos, int qIteraciones) {
		respuesta.clear();
		A.clear();
		int longitud=arrayVasos.length;
		int [] arrVasos = new int [longitud];
		for(int i=0; i<longitud; i++) {
	         arrVasos[i] = Integer.parseInt(arrayVasos[i]);
	    }
		
		for (int q=0; q<qIteraciones; q++) {
			B.clear();
			if(A.isEmpty()) {
				for(int j=arrayVasos.length-1; j>=0;j--) {
						if(arrVasos[j] % numerosPrimos.get(q) ==0) {
							B.add(arrVasos[j]);	
						} else {
							A.add(arrVasos[j]);
						}
				}
			} else {
				ArrayList<Integer> A= new ArrayList<Integer>();
				for(int a=this.A.size()-1; a>=0; a--) {
					if(this.A.get(a) % numerosPrimos.get(q) ==0) {
						B.add(this.A.get(a));	
					} else {
						A.add(this.A.get(a));
					}
				}
				this.A.clear();
				this.A=A;
			}
			for(int b=0; b<B.size(); b++) {
				respuesta.add(B.get(b));
			}

		}
		int i =0;
		do {
			respuesta.add(A.get(i));
			i++;
		} while (i<this.A.size());
		
		return respuesta;
	}
	
	public ArrayList<Integer> llenarArrayNumerosPrimos(int limite) {
		for(int i =0; i<limite; i++) {
			if(esPrimo(i)) {
				numPrimos.add(i);
			}
		}
		return numPrimos;
	}
	
	public static boolean esPrimo(int numero) { 
	    if (numero <= 1) {
	        return false;
	    }
	    int contador = 0;
	    //bucle que cuenta los numeros divisibles
	    for (int i = (int) Math.sqrt(numero); i > 1; i--) {
	        if (numero % i == 0) {
	            contador++;
	        }
	    }
	    return contador < 1;
	}
	
	public String[] convertVasosStringtoArray(String array) {	
	 	String[] newArray=array.split(",");
	 	return newArray;
	}
		
}
