package net.javaguides.springboot.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private PessoaRepository pessoaRepository;

    public String generateToken(String email) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findByEmail(email);
        Pessoa pessoa = pessoaOptional.get();

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", pessoa.getId());
        claims.put("primeiroNome", pessoa.getPrimeiroNome());
        claims.put("ultimoNome", pessoa.getUltimoNome());
        claims.put("email", pessoa.getEmail());
        claims.put("telefone", pessoa.getTelefone());
        claims.put("dataAniversario", pessoa.getDataAniversario().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        claims.put("sexoEnum", pessoa.getSexoEnum());
        claims.put("cpf", pessoa.getCpf());
        claims.put("isApproved", pessoa.getIsApproved());
        claims.put("perfis", pessoa.getPerfis());
        claims.put("setor", pessoa.getSetor());

        return Jwts.builder()
                .setClaims(claims) // Adicione todas as reivindicações personalizadas
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if(claims != null){
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(username != null && expirationDate != null && now.before(expirationDate)){
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try{
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e){
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if(claims != null){
            return claims.getSubject();
        }
        return null;
    }
}
