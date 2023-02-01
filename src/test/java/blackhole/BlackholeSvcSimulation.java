
package blackhole;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.util.concurrent.ThreadLocalRandom;

public class BlackholeSvcSimulation extends Simulation {

 HttpProtocolBuilder httpProtocol = http
	.baseUrl("http://pfx-qal2-east2-k8s.intuit.services.gateway.ppdmeshtestblackhole.mesh");

 ScenarioBuilder scn = scenario("FrontlineMeshBlackholeScenario")
		.exec(http("simpleGet")
			.get("/get")
			.check(status().is(200)));

 { 
   setUp(
	scn.injectOpen(constantUsersPerSec(10).during (1600))).protocols(httpProtocol);
 }

}
