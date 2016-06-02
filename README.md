# tesla-vault

A Clojure library designed to enhance your edn config with environment variables and secrets stored in vault.

## Usage

If you want to use environment variables in your edn config:

```edn
{
 :my-env-specific-setting  #ts/env [:my-env-specific-setting "default-value"]
}
```

Secrets from vault can be pulled in like this:

```edn
{
 :some-db-credentials  #ts/env ["/path/to/db/password"]
}
```

For this to work two environment variables need to be present:
* `$VAULT_ADDR`:the url where vault can be found (e.g.  _https://vault.yourdomain.com_)
* `$VAULT_TOKEN`: a valid token for vault.


## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
