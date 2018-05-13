# Jubilant Eureka
A WebGL 3D model viewer powered by Scala/http4s and Angular6+

<img src="ui/src/assets/img/svg/light-bulb.svg" alt="Logo" width="100px">

## Requirements

* Scala v2.12.6
* sbt v1.1.4
* Node v10.0.0
* npm v6.0.1
* Angular CLI v6.0.0

## Installation

Clone the repository, move into the cloned folder, and then launch `sbt`.


## Usage

In order to run the http4s server:

```
$ sbt
> project backend
> run
```

To show the development server for the Front End you'll need to move into the `ui` folder and then launch `ng serve`.

### Export to gh-pages

```
$ ng build --prod --base-href "http://ingegnati.it/jubilant-eureka/ui/"
$ ngh
``` 

### Getting started

* https://http4s.org/v0.18/
* https://angular.io/tutorial
* http://torre.me.uk/programming/2017/08/19/scala-with-sbt-and-emacs

## Contributing

Please refer to [CONTRIBUTING.md](CONTRIBUTING.md)

## Credits

The logo is licensed under [CC0][1] and it's downloadable from [Pixabay][2].

## License

See `LICENSE.md`.

[1]: https://creativecommons.org/publicdomain/zero/1.0/deed.en
[2]: https://pixabay.com/en/light-bulb-idea-incidence-pear-2223050/
