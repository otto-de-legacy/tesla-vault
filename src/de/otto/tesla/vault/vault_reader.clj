(ns de.otto.tesla.vault.vault-reader
  (:require
            [clojure.tools.logging :as log]
            [clj-http.client :as http]
            [clojure.data.json :as json]
            [environ.core :as env]))

(defn read-secret
  ([[vault-path key]]
   (let [server (env/env :vault-addr)
         token (env/env :vault-token)
         response (http/get (str server "/v1/" vault-path)
                            {:headers {"X-Vault-Token" token}
                             :accept  :json
                             :as      :json})
         body (json/read-str (:body response) :key-fn keyword)]
     (log/infof "Just read %s which is valid for %d seconds"  vault-path (:lease-duration body))
     (if key
       (get (:data body) key)
       (:data body)))))


