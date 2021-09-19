package org.mdw.stc.entity.exception;

public enum CodeEnum {

	UNAUTHORIZED(401), BAD_REQUEST(404), FORBIDDEN(403), INTERNAL_SERVER_ERROR(500), VALIDATION_PARSE_ERROR(422),
	GATEWAY_TIMEOUT(504), NOT_FOUND(404), SERVICE_UNAVAILABLE(503);

	private int code;

	CodeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
