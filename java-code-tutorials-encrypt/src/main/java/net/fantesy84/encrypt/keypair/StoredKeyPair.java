/**
 * Created: 2017-01-12
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt.keypair;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author junjie.ge
 *
 */
public abstract class StoredKeyPair implements Serializable {
	private static final long serialVersionUID = 7516639265920124944L;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	public StoredKeyPair(PublicKey publicKey, PrivateKey privateKey) {
		super();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	/**
	 * @return the publicKey
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}
	/**
	 * @return the privateKey
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

}
