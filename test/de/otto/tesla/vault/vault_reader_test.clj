(ns de.otto.tesla.vault.vault-reader-test
  (:require [environ.core :as env]
            [clojure.test :refer :all]
            [de.otto.tesla.vault.vault_reader :as reader]
            [clj-http.client :as http]))

(deftest ^:unit call-vault-and-parse-json-test
  (let [request-data (atom nil)]
    (with-redefs [env/env {:vault-token "some-token"
                           :vault-addr  "some-url"}
                  http/get (fn [url stuff]
                             (reset! request-data {:url url :stuff stuff})
                             {:status 200
                              :body   "{\"data\":{\"my-key\":\"my-var-value\"}}"})]
      (testing "Should fetch secret from vault."

        (let [secret (reader/read-secret ["/path/to/secret" :my-key])]
          (is (= "my-var-value" secret))
          (is (= "some-url/v1//path/to/secret" (:url @request-data)))
          (is (= {:accept  :json
                  :headers {"X-Vault-Token" "some-token"}} (:stuff @request-data)))))
      (testing "fetch everything if no key is given"
        (is (= {:my-key "my-var-value"}
               (reader/read-secret ["/path/to/secret"])))))))

(deftest ^:unit exceptions-on-missing-data
  (testing "Should throw an exception if the key is not found in vault."
    (let [request-data (atom nil)]
      (with-redefs [env/env {:vault-token "some-token"
                             :vault-addr  "some-url"}
                    http/get (fn [url stuff]
                               (reset! request-data {:url url :stuff stuff})
                               {:status 200
                                :body   "{\"data\":{\"other-key\":\"my-var-value\"}}"})]
        (is (thrown-with-msg? IllegalStateException
                              #"not found in vault"
                              (reader/read-secret ["/path/to/secret" :missing-key])))))))

(deftest ^:unit lacking-vault-config
  (testing "Should return an empty string if there is no token or addr so edn config can be read in tests w/o exceptions"
    (with-redefs [env/env {:vault-addr  "some-url"}]
      (is (= ""
             (reader/read-secret ["/path/to/secret" :my-key]))))

    (with-redefs [env/env {:vault-token "some-token"}]
      (is (= ""
             (reader/read-secret ["/path/to/secret" :my-key]))))))
