package com.thiennbao.kouhii.module.token;

import com.thiennbao.kouhii.module.token.data.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
}
