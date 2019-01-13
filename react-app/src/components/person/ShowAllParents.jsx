import React, { Component } from "react";
import { withRouter } from "react-router";

import Axios from "axios";
import ShowAllParentsTable from "./ShowAllParentsTable";

class ShowAllParent extends Component {
  state = {
    parents: []
  };
  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/app")}
          >
            Return
          </button>
        </div>
        <h1>All parents</h1>
        <ShowAllParentsTable
          parents={this.state.parents}
          goTo={this.goToPage}
          apiHost={this.props.apiHost}
          session={this.props.session}
          onDelete={this.props.deletePerson}
        />
      </div>
    );
  }

  goToPage = path => {
    this.props.history.push(path);
  };

  deletePerson = deleteId => {
    const parents = parents.filter(parent => parent.id === deleteId);

    this.setState({ parents });
  };

  componentDidMount() {
    Axios.post(this.props.apiHost + "/person/list/parents", this.props.session)
      .then(resp => {
        const parents = resp.data;
        this.setState({ parents });
      })
      .catch(err => console.log(err));
  }
}

export default withRouter(ShowAllParent);
