package com.ipnet.FinanceApp.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ipnet.FinanceApp.security.UserService;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AuthTokenFilter(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        logger.debug("=== AuthTokenFilter === URI: {}", requestURI);

        try {
            // 1. Extraire le token
            String token = getJWTFromToken(request);
            logger.debug("Token extrait: {}", token != null ? "OUI (présent)" : "NON (absent)");

            // 2. Valider le token
            if (token != null) {
                logger.debug("Validation du token...");
                
                boolean isValid = jwtUtils.validateToken(token);
                logger.debug("Token valide: {}", isValid);

                if (isValid) {
                    // 3. Récupérer le username du token
                    String username = jwtUtils.getUserNameFromJwtToken(token);
                    logger.info("✓ Token valide pour l'utilisateur: {}", username);

                    // 4. Charger les détails de l'utilisateur
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    logger.debug("Utilisateur trouvé: {}", userDetails.getUsername());
                    logger.debug("Autorités: {}", userDetails.getAuthorities());

                    // 5. Créer l'authentification
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // 6. Mettre en contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("✓ Authentification définie pour: {}", username);
                } else {
                    logger.warn("✗ Token invalide ou expiré");
                }
            } else {
                logger.debug("✗ Aucun token dans le header Authorization");
            }
        } catch (Exception e) {
            logger.error("✗ ERREUR lors du traitement du JWT: {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTFromToken(HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        
        logger.debug("Authorization header: {}", bearerToken != null ? "présent" : "absent");
        
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            logger.debug("Token extrait avec succès (longueur: {})", token.length());
            return token;
        }
        
        logger.debug("Format Authorization invalide ou absent");
        return null;
    }
}