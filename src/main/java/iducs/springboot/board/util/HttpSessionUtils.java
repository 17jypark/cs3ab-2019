package iducs.springboot.board.util;

import iducs.springboot.board.domain.User;

public class HttpSessionUtils {
	public static boolean isLogined(User user) {
		// session에 user 객체가 null이면 로그인 필요
		if(user == null)
			return true;
		return false;
	}
}