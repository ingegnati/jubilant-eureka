# Jubilant Eureka
A WebGL 3D model viewer powered by Scala Akka HTTP and Angular6+

<img src="ui/src/assets/img/svg/light-bulb.svg" alt="Logo" width="100px">

## Requirements

* sbt v1.1.5 ([download](https://www.scala-sbt.org/download.html))
* Node v10.0.0 ([download](https://nodejs.org/en/download/))
   * npm v6.0.1 ([get the right npm version](https://www.npmjs.com/get-npm))
* Angular CLI v6.0.0 (`npm install -g @angular/cli`)

#### Suggested versions

* Scala v2.12.6
* Java v1.8 (Oracle)

## Installation

Clone the repository, move into the cloned folder, and then launch `sbt`.


## Usage

In order to run the Akka HTTP server:

```
$ sbt
> project backend
> reStart
```

To show the development server for the Front End you'll need to move into the `ui` folder and then launch `ng serve`. 
To watch for changes it's possible to use the following commands:

- `~compile` (in order to recompile on save)
- `~test`    (to launch all the tests on save)
- `~test:compile`

Running `~test:compile` rather than `~compile` when writing code is often what you really need. 
Make sure everything in your codebase compiles as you go, rather than checking the tests right before you commit.

The command `reStart` is provided by the [sbt-revolver plugin][3]. To be proficient using sbt for this project
please refer to [SBT Tricks][4]. 

### Export to gh-pages

To generate a preview version that can be viewed using GitHub Pages:

```
$ ng build --prod --base-href "http://ingegnati.it/jubilant-eureka/ui/"
$ ngh
``` 

### Getting started

* https://doc.akka.io/docs/akka-http/current
* https://angular.io/tutorial
* http://torre.me.uk/programming/2017/08/19/scala-with-sbt-and-emacs
* [Essential Slick][5]

## Contributing

Please refer to [CONTRIBUTING.md](CONTRIBUTING.md)

## Credits

The logo is licensed under [CC0][1] and it's downloadable from [Pixabay][2].

## License

See [LICENSE.md](LICENSE.md).

[1]: https://creativecommons.org/publicdomain/zero/1.0/deed.en
[2]: https://pixabay.com/en/light-bulb-idea-incidence-pear-2223050/
[3]: https://github.com/spray/sbt-revolver#usage
[4]: https://underscore.io/blog/posts/2015/11/09/sbt-commands.html
[5]: https://doc.akka.io/docs/akka-http/current
