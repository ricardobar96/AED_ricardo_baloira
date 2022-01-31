import React from 'react';

  interface IProps{ }
  interface IState{
    monedas: Array<any>|null;
  }

  class App extends React.Component<IProps, IState>{
    constructor(props: IProps){
      super(props);
      this.state = {
        monedas: ["prueba", "inicial"]
      };
    }
    
    render(){
      const {monedas} = this.state;
      return (
      <>
      <h3>Un componente sencillo para monedas</h3>
      <div>
        Monedas: {JSON.stringify(monedas)}
      </div>
      </>
      );
    }
  }
  export default App;