<template>
  <div id="app">
    <NavBar/>
    <main role="main" class="container">
      <div class="jumbotron">
        <h1>List</h1>

        <b-table striped hover :items="items" :fields="fields" :fixed="fixed" :small=true>
          <template slot="actions" slot-scope="row">
            <b-button v-b-tooltip.hover title="Mark as in progress" variant="primary" size="sm" @click="markTaskAs(row.item, row.index, 1)">
              <font-awesome-icon icon="play"/>
            </b-button>
            <b-button v-b-tooltip.hover title="Mark as done" variant="success" size="sm" @click="markTaskAs(row.item, row.index, 2)">
              <font-awesome-icon icon="check"/>
            </b-button>
            <b-button v-b-tooltip.hover title="Delete" variant="danger" size="sm" @click="deleteTask(row.item, row.index)">
              <font-awesome-icon icon="trash-alt"/>
            </b-button>
          </template>
        </b-table>
        <div>
          <b-form @submit="onSubmit" autocomplete="off">
            <b-input-group class="mt-3">
              <b-form-input id="text" v-model="form.text" type="text" required placeholder="Task..."></b-form-input>
              <b-input-group-append>
                <b-form-input id="priority" v-model="form.priority" type="number" required placeholder="Priority"></b-form-input>
                <b-button variant="outline-success" type="submit">Add</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form>

        </div>
      </div>
    </main>
  </div>
</template>

<script>
  import {APIService} from '../api/APIService';
  import NavBar from './NavBar.vue';

  const apiService = new APIService();

  export default {

    name: 'List',

    components: {
      NavBar
    },

    data() {

      return {
        items: [],
        fixed: true,
        form:{
          text: "",
          priority: "",
        },
        fields: {
          id: {
            label: 'ID',
            sortable: true
          },
          priority: {
            label: 'Priority',
            sortable: true
          },
          text: {
            // This key overrides `foo`!
            label: 'Task',
            sortable: true
          },
          stateString: {
            label: 'State',
            sortable: true
          },
          actions: {
            label: 'Actions'
          }
        },
      };
    },

    methods: {

      getAllTasks() {
        apiService.getAllTasks().then((data) => {
          data.forEach((item, index) => {
            processData(item);
          });
          this.items = data;

        });
      },
      deleteTask(item, index) {
        this.items.splice(index, 1);
        apiService.deleteTask(item.id);
      },
      onSubmit(event) {
        event.preventDefault();
        alert(JSON.stringify(this.form))
        apiService.createTask(this.form.priority, this.form.text).then((data) => {
          processData(data);
          this.items.push(data)
        });
        this.form.priority = "";
        this.form.text = "";
      },
      markTaskAs(item, index, state) {
        item.state = state;
        processData(item);
        apiService.updateTask(item);
      },
    },

    mounted() {
      this.getAllTasks();
    },
  }

  function processData(item) {
    switch (item.state) {
      case 0:
        item.stateString = "New";
        item._rowVariant = 'warning';
        break;
      case 1:
        item.stateString = "In progress";
        item._rowVariant = 'info';
        break;
      case 2:
        item.stateString = "Done";
        item._rowVariant = 'success';
    }
  }
</script>
