package configuration;

/**
 * Token for received data from resource server esia
 */
public class Token {

    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
    private String iss;
    private String scope;
    private String tokenType;
    private String exp;
    private String nbf;
    private String clientId;
    private String sid;
    private String sbjId;
    private String iat;

    public Token() {
    }

    /**
     * Organization which create this token
     *
     * @return
     */
    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    /**
     * Time prekrasheniya action presents
     *
     * @return
     */
    public Long getExp() {
        return Long.parseLong(exp);
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    /**
     * The start time
     *
     * @return
     */
    public Long getNbf() {
        return Long.parseLong(nbf);
    }

    public void setNbf(String nbf) {
        this.nbf = nbf;
    }

    /**
     * System which using this token (=RPGU)
     *
     * @return
     */
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * The set of random symbols with a 128-bit identifier archive by the UUID standard.
     *
     * @return
     */
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * Id to whom the access granted(user id)
     *
     * @return
     */
    public String getSbjId() {
        return sbjId;
    }

    public void setSbjId(String sbjId) {
        this.sbjId = sbjId;
    }

    /**
     * Issue token
     *
     * @return
     */
    public Long getIat() {
        return Long.getLong(iat);
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    /**
     * Access token
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Time how long the token is valid
     * @return
     */
    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * Refresh token
     * @return
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Scope
     * @return
     */
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * Token type (=Bearer)
     * @return
     */
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
