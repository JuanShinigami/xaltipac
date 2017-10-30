package org.lldm.xaltipac.service.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *@author Juan Mateo Sauce.
 * Clase orientada en AOP que nos facilitara la escritura de logs al entrar
 * a nuestros metodos de los controllers.
 * Podremos saber el nombre de la clase y el nombre del metodo al que esta
 * entrando.
 * (Ref)Falta desarrolla el mentor de after si es preciso se agregara despues y se quitara esta referencia :)
 */
@Aspect
public class LoggerAOP {

	
	@Before("execution(* com.iofractal.sies.web..*.*(..))")
	public void logBefore(JoinPoint joinPoint) {

		
			String className = joinPoint.getTarget().getClass().getSimpleName();
			
			Logger log = Logger.getLogger(className);
			
			log.info("Class : ************** " + className + "****************");
			log.info("Method : ************** " + joinPoint.getSignature().getName() + "****************");
			
			//cuando se utilizan parametros como el model o el request  no se ve muy guapo el log
			//abra que considerar el log siguiente para usar el params
			//log.info("Params : ************** " + Arrays.toString(joinPoint.getArgs()) + "****************");
			
		
		
		
	}
}
