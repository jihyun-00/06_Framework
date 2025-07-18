package edu.kh.project.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import edu.kh.project.websocket.handler.TestWebSocketHandler;
import edu.kh.project.websocket.interceptor.SessionHandshakeInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration		// 서버 실행 시 작성된 메서드를 모두 수행
@EnableWebSocket	// 웹소켓 활성화 설정
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{
	
	private final SessionHandshakeInterceptor handshakeInterceptor;
	private final TestWebSocketHandler studyChatWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(studyChatWebSocketHandler, "/studyChatSock")
                .addInterceptors(handshakeInterceptor)
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
	
//	// Bean으로 등록된 SessionHandshakeInterceptor 의존성 주입
//	private final HandshakeInterceptor handshakeInterceptor;
//	
//	// Bean으로 등록된 TestWebSocketHandler 의존성 주입
//	private final TestWebSocketHandler testWebSocketHandler;
//	
//	// 채팅 관련 웹소켓 처리 동작이 작성된 객체 의존성 주입
//	private final ChattingWebsocketHandler chattingWebsocketHandler;
//	
//	// 웹소켓 핸들러를 등록하는 메서드
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		
//		registry.addHandler(testWebSocketHandler, "/testSock")
//		// ws://localhost/testSock으로
//		// 클라이언트가 요청을 하면
//		// testWebSockstHandler가 처리하도록 등록
//		.addInterceptors(handshakeInterceptor)
//		// 클라이언트 연결 시 HttpSession을 가로채 핸들러에게
//		// 전달하는 handshakeInterceptor 등록
//		.setAllowedOriginPatterns("http://localhost/",
//								  "http://127.0.0.1/",
//								  "http://192.168.130.196/")
//		// 웹소켓 요청이 허용되는 ip/도메인 지정
//		.withSockJS(); // SockJS 지원
//		
//		// --------------------------------------------------
//		
//		registry
//		.addHandler(chattingWebsocketHandler, "/chattingSock")
//		.addInterceptors(handshakeInterceptor)
//		.setAllowedOriginPatterns("http://localhost/",
//								  "http://127.0.0.1/",
//								  "http://192.168.130.196/")
//		.withSockJS();
//	}

