# tesla-vault

A Clojure library designed to enhance your edn config with environment variables and secrets stored in vault.

## Usage

If you want to use secrets from vault in your environment variables:

```edn
{
 :some-db-credentials  #ts/vault ["/path/to/db/password"]
 :just-the-db-password #ts/vault ["/path/to/db/password" :password] 
}
```

For this to work two environment variables need to be present:
* `$VAULT_ADDR`:the url where vault can be found (e.g.  _https://vault.yourdomain.com_)
* `$VAULT_TOKEN`: a valid token for vault.

And you have to require the reader-function like this:
```
(:require
[de.otto.tesla.vault.vault_reader :only [read-secret]])
```

## Initial Contributors

Christian Stamm, Florian Weyandt, Carl Düvel

## License
Apache License.
