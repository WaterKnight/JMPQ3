/*
Copyright 2014 Google Inc. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Author: eustas.ru@gmail.com (Eugene Klyuchnikov)
*/

package ru.eustas.zopfli;

public class Zopfli {

    private final Cookie cookie;

    public synchronized Buffer compress(Options options, byte[] input) {
        Buffer output = new Buffer();
        switch (options.outputType) {
            case DEFLATE:
                Deflate.compress(cookie, options, input, output);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unexpected output format: " + options.outputType);
        }
        return output;
    }

    public Zopfli(int masterBlockSize) {
        cookie = new Cookie(masterBlockSize);
    }
}
