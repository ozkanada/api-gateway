package com.oteller.rezervin.gateway.exception.exceptions;

import com.oteller.rezervin.gateway.exception.enums.IFriendlyMessageCode;
import com.oteller.rezervin.gateway.exception.utils.FriendlyMessageUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class InvalidTokenException extends RuntimeException{
	private static final long serialVersionUID = 813835515664139339L;
	private final IFriendlyMessageCode friendlyMessageCode;

    public InvalidTokenException(IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode));
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[InvalidTokenException] -> message {} developer message {}",FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode), message);
    }
}
