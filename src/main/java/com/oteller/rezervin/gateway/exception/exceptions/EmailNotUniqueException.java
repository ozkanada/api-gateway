package com.oteller.rezervin.gateway.exception.exceptions;

import com.oteller.rezervin.gateway.exception.enums.IFriendlyMessageCode;
import com.oteller.rezervin.gateway.exception.utils.FriendlyMessageUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class EmailNotUniqueException extends RuntimeException{
	private static final long serialVersionUID = 8664335099783594134L;
	private final IFriendlyMessageCode friendlyMessageCode;

    public EmailNotUniqueException(IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode));
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[EmailNotUniqueException] -> message {} developer message {}",FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode), message);
    }
}
