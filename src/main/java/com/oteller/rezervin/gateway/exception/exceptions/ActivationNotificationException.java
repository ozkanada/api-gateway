package com.oteller.rezervin.gateway.exception.exceptions;

import com.oteller.rezervin.gateway.exception.enums.IFriendlyMessageCode;
import com.oteller.rezervin.gateway.exception.utils.FriendlyMessageUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ActivationNotificationException extends RuntimeException{
	private static final long serialVersionUID = -5938607487331760171L;
	private final IFriendlyMessageCode friendlyMessageCode;

    public ActivationNotificationException(IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode));
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[ActivationNotificationException] -> message {} developer message {}",FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode), message);
    }
}
