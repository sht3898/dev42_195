<template>
    <v-container>
        <v-row>
        <v-col cols="6">
            <material-card
                  color="orange"
                  title="Employee Stats"
                  text="New employees on 15th September, 2016"
                >
            <zingchart :data="chartData"></zingchart>
            </material-card>
        </v-col>
        <v-col cols="6">
                <material-card
                  color="orange"
                  title="Employee Stats"
                  text="New employees on 15th September, 2016"
                >
                  <v-data-table
                    :headers="headers"
                    :items="items"
                    hide-actions
                  >
                    <template
                      slot="headerCell"
                      slot-scope="{ header }"
                    >
                      <span
                        class="font-weight-light text-warning text--darken-3"
                        v-text="header.text"
                      />
                    </template>
                    <template
                      slot="items"
                      slot-scope="{ index, item }"
                    >
                      <td>{{ index + 1 }}</td>
                      <td>{{ item.name }}</td>
                      <td class="text-xs-right">{{ item.salary }}</td>
                      <td class="text-xs-right">{{ item.country }}</td>
                      <td class="text-xs-right">{{ item.city }}</td>
                    </template>
                  </v-data-table>
                </material-card>
        </v-col>
        <v-col cols="12">
            <div v-html="compiledMarkdown"></div>
        </v-col>
        </v-row>

    </v-container>
</template>
<script src="https://unpkg.com/marked@0.3.6"></script>
<script src="https://unpkg.com/lodash@4.16.0"></script>
<script>
import MaterialCard from './material/Card';

export default {
    components : {
        MaterialCard,
        
    },
    data() {
        return {
            input: 
            "# [Minimal Mistakes Jekyll theme](https://mmistakes.github.io/minimal-mistakes/)"+"\n"

+"[![LICENSE](https://camo.githubusercontent.com/c0e54a826d90da52e4ee70c63fef3ee61b3ffbd5/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d4d49542d6c69676874677265792e737667)](https://raw.githubusercontent.com/mmistakes/minimal-mistakes/master/LICENSE) [![Jekyll](https://camo.githubusercontent.com/0cc8473c66b2b4d576732997156a33e755d23702/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a656b796c6c2d253345253344253230332e362d626c75652e737667)](https://jekyllrb.com/) [![Ruby gem](https://camo.githubusercontent.com/53e47ca7987d7a3a0b56e3eeeaace452df66ba84/68747470733a2f2f696d672e736869656c64732e696f2f67656d2f762f6d696e696d616c2d6d697374616b65732d6a656b796c6c2e737667)](https://rubygems.org/gems/minimal-mistakes-jekyll) [![Tip Me via PayPal](https://camo.githubusercontent.com/163dd5bc9660f3d6fafbe70659d7f7345776b196/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f50617950616c2d7469702532306d652d677265656e2e7376673f6c6f676f3d70617970616c)](https://www.paypal.me/mmistakes)"+

            "Minimal Mistakes is a flexible two-column Jekyll theme, perfect for building personal sites, blogs, and portfolios. As the name implies, styling is purposely minimalistic to be enhanced and customized by you 😄."+

            "✨ See what's new in the [CHANGELOG](https://github.com/SenseCodeValue/minimal-mistakes/blob/master/CHANGELOG.md)."+

            "**If you enjoy this theme, please consider [supporting me](https://www.paypal.me/mmistakes) to continue developing and maintaining it.**"
            ,
            chartData: {
                type: "pie3d",
                title: {
                  text: ""
                },
                plot: {
                  'offset-r': "25%"
                },
                series: [
                  { values: [59]},
                  { values: [55]},
                  { values: [30]},
                  { values: [28]},
                  { values: [15]}
                ]
            },
             headers: [
              {
                sortable: false,
                text: 'ID',
                value: 'id'
              },
              {
                sortable: false,
                text: 'Name',
                value: 'name'
              },
              {
                sortable: false,
                text: 'Salary',
                value: 'salary',
                align: 'right'
              },
              {
                sortable: false,
                text: 'Country',
                value: 'country',
                align: 'right'
              },
              {
                sortable: false,
                text: 'City',
                value: 'city',
                align: 'right'
              }
            ],
            items: [
                {
                  name: 'Dakota Rice',
                  country: 'Niger',
                  city: 'Oud-Tunrhout',
                  salary: '$35,738'
                },
                {
                  name: 'Minerva Hooper',
                  country: 'Curaçao',
                  city: 'Sinaai-Waas',
                  salary: '$23,738'
                }, {
                  name: 'Sage Rodriguez',
                  country: 'Netherlands',
                  city: 'Overland Park',
                  salary: '$56,142'
                }, {
                  name: 'Philip Chanley',
                  country: 'Korea, South',
                  city: 'Gloucester',
                  salary: '$38,735'
                }, {
                  name: 'Doris Greene',
                  country: 'Malawi',
                  city: 'Feldkirchen in Kārnten',
                  salary: '$63,542'
                }
            ],
        }//return
    },//data
    computed: {
    compiledMarkdown: function () {
      return marked(this.input, { sanitize: true })
    },
    methods: {
    update: _.debounce(function (e) {
      this.input = e.target.value
    }, 300)
  }
  },
}
</script>