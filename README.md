Pix-Color-Analyzer
==================

Developed by <a href='https://github.com/LeonardoCardoso' target='_blank'>@LeonardoCardoso</a>. 

Library with the aim to analyze the pixel colors of an ARGB image.

## How to use that?

Simple user the jar file.

    java -jar pca.jar [filename] [amount]
    
You also can suppress amount. This way, the full palette will be generated.
Obs.: For images that have many colors it can take a while to generate the window with the colors.

    java -jar pca.jar [filename]


## Picture

![Nebula](https://dl.dropboxusercontent.com/s/vha3q1e64x24msb/neb_small.jpg)

## Palette

Here the most dominant colors are printed in descending order

![Palette](https://dl.dropboxusercontent.com/s/s006hfutt2u3vhv/pallete.png)

![Palette](https://dl.dropboxusercontent.com/s/z4679u29hsrxv19/result_pallete.png)



For more details, visit http://lab.leocardz.com/pix-color-analyzer/


## License

    Copyright 2015 Leonardo Cardoso

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
