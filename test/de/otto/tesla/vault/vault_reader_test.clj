(ns de.otto.tesla.vault.vault-reader-test
  (:require [environ.core :as env]
            [clojure.test :refer :all]
            [de.otto.tesla.vault.vault-reader :as reader]
            [clj-http.client :as http]))

(deftest ^:unit should-call-vault-and-parse-json
  (let [request-data (atom nil)]
    (with-redefs [env/env {:vault-token "some-token"
                           :vault-addr  "some-url"}
                  http/get (fn [url stuff]
                             (reset! request-data {:url url :stuff stuff})
                             {:body "{\"data\":{\"my-key\":\"my-var-value\"}}"})]
      (let [secret (reader/read-secret "/path/to/secret" :my-key)]
        (is (= "my-var-value" secret))
        (is (= "some-url/v1//path/to/secret" (:url @request-data)))
        (is (= {:accept  :json
                :as      :json
                :headers {"X-Vault-Token" "some-token"}} (:stuff @request-data)))
        (let [secret (reader/read-secret "/path/to/secret")]
          (is (= {:my-key "my-var-value"} secret)))))))
