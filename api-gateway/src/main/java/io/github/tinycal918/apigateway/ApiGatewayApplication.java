package io.github.tinycal918.apigateway;

import io.github.tinycal918.apigateway.inbound.HttpInboundServer;

import java.util.Arrays;

public class ApiGatewayApplication {

	public final static String GATEWAY_NAME = "NIOGateway";
	public final static String GATEWAY_VERSION = "3.0.0";

	public static void main(String[] args) {
		String proxyPort = System.getProperty("proxyPort", "8888");

		String proxyServers = System.getProperty("proxyServers", "http://localhost:8801,http://localhost:8802");

		int port = Integer.parseInt(proxyPort);
		System.out.println(GATEWAY_NAME + " " +GATEWAY_VERSION+"starting...");

		HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
		System.out.println(GATEWAY_NAME + " " +GATEWAY_VERSION+"started at http://localhost:" + port + " for server:" + server.toString());

		try {
			server.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
