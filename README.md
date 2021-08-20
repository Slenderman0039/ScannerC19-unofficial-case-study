# ScannerC19
This unofficial software allows you to scan QR codes and extract information from the JSON contained within it. This is an unofficial app, made for pure study. 
### Dependencies
Questo software contiene dipendenze di [VerificaC19](https://github.com/ministero-salute/it-dgc-verificaC19-android) - Java / Kotlin .
So please use the correct installation by them. Another project dependency is [dgc-java](https://github.com/DIGGSweden/dgc-java), whose [object and method documentation](https://diggsweden.github.io/dgc-java/javadoc/dgc-create-validate/se/digg/dgc/encoding/package-use.html).

## Decoding QR Codes
The decoding mechanism is explained here: The Qr Code contains a series of characters encoded in the HCERT protocol, for this it is necessary to remove the string header (first 4 characters). It is then necessary to decode the string obtained in Base45 format (Base45 decoder integrated in the Open Source project of VerificationC19) and then decompress it using zlib (already integrated in the dependencies). Subsequently Since the certificate is encrypted using the CBOR Object Signing and Encryption (COSE) format, it must be decrypted. Once the decrypted object is obtained, the getCwt () method can be accessed, which contains the certificate with personal information. However, it is possible to convert this string into a JSON object and thus manage the whole mechanism for receiving patient information.
## MIT License

ScannerQR

Copyright © 2021 Alessandro Macaluso

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



