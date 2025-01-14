package com.example.enterparkticket.apis.enduser.security.exception

class ExpiredTokenException : JwtException(JwtErrorCode.EXPIRED_TOKEN)
