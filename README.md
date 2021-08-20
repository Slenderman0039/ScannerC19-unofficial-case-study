# ScannerC19
This unofficial software allows you to scan QR codes and extract information from the JSON contained within it. This is an unofficial app, made for pure study. 
### Dependencies
Questo software contiene dipendenze di [VerificaC19](https://github.com/ministero-salute/it-dgc-verificaC19-android) - Java / Kotlin .
So please use the correct installation by them. Another project dependency is [dgc-java](https://github.com/DIGGSweden/dgc-java), whose [object and method documentation](https://diggsweden.github.io/dgc-java/javadoc/dgc-create-validate/se/digg/dgc/encoding/package-use.html).

## Decoding QR Codes
The decoding mechanism is explained here: The Qr Code contains a series of characters encoded in the HCERT protocol, for this it is necessary to remove the string header (first 4 characters). It is then necessary to decode the string obtained in Base45 format (Base45 decoder integrated in the Open Source project of VerificationC19) and then decompress it using zlib (already integrated in the dependencies). Subsequently Since the certificate is encrypted using the CBOR Object Signing and Encryption (COSE) format, it must be decrypted. Once the decrypted object is obtained, the getCwt () method can be accessed, which contains the certificate with personal information. However, it is possible to convert this string into a JSON object and thus manage the whole mechanism for receiving patient information.
### Screenshot



